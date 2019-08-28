package ru.mihkopylov.spring.version;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import ru.mihkopylov.spring.version.exception.UnsupportedVersionExcepion;

@AllArgsConstructor
public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {
    private final int apiMinVersion;
    private final int handlerMinVersion;
    @NonNull
    private final RequestVersionExtractor requestVersionExtractor;

    @Override
    public final VersionRequestCondition combine( VersionRequestCondition condition ) {
        return condition;
    }

    @Override
    public final VersionRequestCondition getMatchingCondition( HttpServletRequest httpServletRequest ) {
        Optional<Integer> requestVersion = requestVersionExtractor.getRequestVersion( httpServletRequest );
        if (requestVersion.isEmpty()) {
            return null;
        }
        int requestVersionValue = requestVersion.get();
        if (requestVersionValue < apiMinVersion) {
            throw new UnsupportedVersionExcepion( apiMinVersion, requestVersionValue );
        }
        if (requestVersionValue < handlerMinVersion) {
            return null;
        }
        return this;
    }

    @Override
    public final int compareTo( VersionRequestCondition conditon, HttpServletRequest httpServletRequest ) {
        return conditon.handlerMinVersion - handlerMinVersion;
    }
}
