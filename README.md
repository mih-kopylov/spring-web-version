# Spring Web Version 

When building RESTful API it is worth thinking about API versioning from the early beginning, even if it seems to have a single version forever at the moment.
That means API should have a version, and usually the version starts from 1.

At the same time it is worth deciding a way, how version should be passed to the request.
Ther're 4 common ways of versioning RESTful API. They're nicely described here: https://restfulapi.net/versioning/.
Once one of them is chosen, first controllers can be created. And usually they look like this:

```
@GetMapping("/v1/employees")
public List<Employee> getEmployees(){}

@PostMapping("/v1/employees")
public Employee createEmployee(){}
``` 
and response for `GET /v1/employees` looks like this
```json
[{}, {}]
```

Time goes and once it happens that there are too many employees returned for each GET request and pagination is required.
The response should look like this:

```json
{
  "content": [{}, {}],
  "page": 0,
  "size": 20,
  "total": 9999
}
```

And since it's desirable not to break compatibility with the previous application version when a new one is released, API version 2 for GET request is required.

It can be done with just a new mapping like this:
```
@GetMapping("/v1/employees")
public List<Employee> getEmployeesV1(){}

@GetMapping("/v2/employees")
public Page<Employee> getEmployeesV2(){}
                         
@PostMapping("/v1/employees")
public Employee createEmployee(){}
``` 

But in this case there is no `/v2` endpoint for creation. Each endpoint has own version. 
That is not convenient for clients that use this API. 
It is really convenient for clients to have a single version of API and have no headache with not compatible endpoints versions.

It can also be done with duplicating all unchanged mappings with a new version like this:

```
@GetMapping("/v1/employees")
public List<Employee> getEmployeesV1(){}

@GetMapping("/v2/employees")
public Page<Employee> getEmployeesV2(){}
                         
@PostMapping("/v1/employees")
public Employee createEmployeeV1(){}

@PostMapping("/v2/employees")
public Employee createEmployeeV2(){
  return createEmployeeV1();
}
``` 

But this approach produces a lot of boilerplate code that is really hard to maintain when API is quite big.

And here is where `spring-web-version` comes to the rescue.

It allowes to add a single new mapping with a new version while all old mappings will handle requests to a new version like this:

```
@GetMapping("/employees")
@VersionedResource(from=1)
public List<Employee> getEmployeesV1(){}

@GetMapping("/employees")
@VersionedResource(from=2)
public Page<Employee> getEmployeesV2(){}
                         
@PostMapping("/employees")
@VersionedResource(from=1)
public Employee createEmployee(){}
``` 

This configuration will map
* `GET /v1/employees` to `getEmployeesV1` handler
* `GET /v2/employees` to `getEmployeesV2` handler
* `POST /v1/employees` to `createEmployee` handler
* `POST /v2/employees` to `createEmployee` handler


It's easy to apply it to your web-mvc configuration:

* add maven dependency
```xml
<dependency>
    <groupId>ru.mihkopylov</groupId>
    <artifactId>spring-web-version</artifactId>
    <version>${spring-web-version.version}</version>
</dependency>
```

* add `spring.mvc.versioning.type` to your `application.properties` file with one of:
  * PATH
  * QUERY
  * HEADER
  * ACCEPT  
  
* add `@VersionedResource` annotation for controller class or handler method.

And here you go.

# Usage

## Version in path prefix 

`spring.mvc.versioning.type=PATH`

Default path prefix is `v` so URL that starts with `v` plus version number is matched. 

Path prefix can be configured via property: `spring.mvc.versioning.pathVersionPrefix=v.`, so the URL will look like `/v.1/employees`.


## Version in custom query parameter 

`spring.mvc.versioning.type=QUERY`

In this configuration version is passed in query parameter. Default parameter is `version`, so URL looks like `/employees?version=1`.

Query parameter can be configured via property `spring.mvc.versioning.query=v`, so URL will look like `/employees?v=1`

## Version in custom header

`spring.mvc.versioning.type=HEADER`
 
In this configuration version is passed in a custom header. Default header name is `version`. 
 
Header name can be configured via property `spring.mvc.versioning.header=VER`.

## Version in Accept header value 

`spring.mvc.versioning.type=ACCEPT`

In this configuration version is passed inside custom `Accept` header value, for example `"Accept: application/vnd.v1+json"`.

Header value can also be configured via property `spring.mvc.versioning.accept=application/vnd\\+json;version=(\\d+)`, so accept value will look like `application/vnd+json;version=1`.
The property should contain a regexp pattern with a single group which contains a numeric version.
