package ru.mihkopylov.spring.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to version controller handlers.
 * <br>
 * <pre>
 * &#64;GetMapping("/employees")
 * &#64;VersionedResource(from=1)
 * public List&lt;Employee&gt; getEmployeesV1(){}
 * </pre>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VersionedResource {
    /**
     * @return API version as from which the annotated handler will match a request until another handler for the same URL with a greater {@code from} parameter is defined
     */
    int from();
}
