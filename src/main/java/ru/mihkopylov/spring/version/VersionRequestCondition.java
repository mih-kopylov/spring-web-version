package ru.mihkopylov.spring.version;

import java.lang.reflect.Method;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.exception.UnsupportedVersionExcepion;

@AllArgsConstructor
@Slf4j
public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {
    private final int apiMinVersion;
    private final int handlerMinVersion;
    @NonNull
    private final RequestVersionExtractor requestVersionExtractor;

    /**
     * Combines two conditions in case when both class and method have
     * {@link org.springframework.web.bind.annotation.RequestMapping} annotation and both have {@link VersionedResource}.
     * According to {@link RequestMappingHandlerMapping#getMappingForMethod(Method, Class)} implementation
     * {@code combine} method is called on class level condition and method level condition comes in parameter.
     * Since method level {@link VersionedResource} has more power, it is returned back.
     */
    @SuppressWarnings("JavadocReference")
    @Override
    public final VersionRequestCondition combine( VersionRequestCondition condition ) {
        return condition;
    }

    @Override
    public final VersionRequestCondition getMatchingCondition( HttpServletRequest httpServletRequest ) {
        log.debug( "API minimal version is {}", apiMinVersion );
        log.debug( "Handler minimal version is {}", handlerMinVersion );
        Optional<Integer> requestVersion = requestVersionExtractor.getRequestVersion( httpServletRequest );
        log.debug( "Request version is {}", requestVersion.orElse( null ) );
        if (requestVersion.isEmpty()) {
            return null;
        }
        int requestVersionValue = requestVersion.get();
        if (requestVersionValue < apiMinVersion) {
            throw new UnsupportedVersionExcepion( apiMinVersion, requestVersionValue );
        }
        if (requestVersionValue < handlerMinVersion) {
            log.debug( "Request version is less than handler version, skipping" );
            return null;
        }
        log.debug( "Request version matches handler version" );
        return this;
    }

    @Override
    public final int compareTo( VersionRequestCondition conditon, HttpServletRequest httpServletRequest ) {
        return conditon.handlerMinVersion - handlerMinVersion;
    }
}
