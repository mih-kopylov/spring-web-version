package ru.mihkopylov.spring.version;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@AllArgsConstructor
public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private final int apiMinVersion;
    @NonNull
    private final RequestVersionExtractor requestVersionExtractor;

    @Override
    protected RequestCondition<?> getCustomTypeCondition( Class<?> handlerType ) {
        return createCondition( AnnotationUtils.findAnnotation( handlerType, VersionedResource.class ) );
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition( Method method ) {
        return createCondition( AnnotationUtils.findAnnotation( method, VersionedResource.class ) );
    }

    @NonNull
    private RequestCondition<?> createCondition( @Nullable VersionedResource versionedResource ) {
        return (versionedResource != null) ? new VersionRequestCondition( apiMinVersion, versionedResource.from(),
                requestVersionExtractor ) : null;
    }
}
