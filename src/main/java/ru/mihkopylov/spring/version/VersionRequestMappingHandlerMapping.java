package ru.mihkopylov.spring.version;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static java.util.Objects.nonNull;

@AllArgsConstructor
public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private final int apiMinVersion;
    @NonNull
    private final RequestVersionExtractor requestVersionExtractor;

    @Override
    protected RequestCondition<?> getCustomTypeCondition( Class<?> handlerType ) {
        VersionedResource classAnnotation = AnnotationUtils.findAnnotation( handlerType, VersionedResource.class );
        return createCondition( classAnnotation );
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition( Method method ) {
        VersionedResource methodAnnotation = AnnotationUtils.findAnnotation( method, VersionedResource.class );
        VersionedResource classAnnotation =
                AnnotationUtils.findAnnotation( method.getDeclaringClass(), VersionedResource.class );
        VersionedResource mergedAnnotation = nonNull( methodAnnotation ) ? methodAnnotation : classAnnotation;
        return createCondition( mergedAnnotation );
    }

    @NonNull
    private RequestCondition<?> createCondition( @Nullable VersionedResource versionedResource ) {
        return (versionedResource != null) ? new VersionRequestCondition( apiMinVersion, versionedResource.from(),
                requestVersionExtractor ) : null;
    }
}
