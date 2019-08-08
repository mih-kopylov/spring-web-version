package ru.mihkopylov.spring.version.query;

import ru.mihkopylov.spring.version.VersionedResource;
import java.lang.reflect.Method;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class QueryVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return createCondition(AnnotationUtils.findAnnotation(handlerType, VersionedResource.class));
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return createCondition(AnnotationUtils.findAnnotation(method, VersionedResource.class));
    }

    private RequestCondition<?> createCondition(VersionedResource versionedResource) {
        return (versionedResource != null) ?
                new QueryVersionRequestCondition(versionedResource.from(), versionedResource.to()) : null;
    }
}
