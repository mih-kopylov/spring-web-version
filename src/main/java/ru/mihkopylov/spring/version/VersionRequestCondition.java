package ru.mihkopylov.spring.version;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

@AllArgsConstructor
public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {
    private final int minVersion;
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
        if (requestVersion.get() < minVersion) {
            return null;
        }
        return this;
    }

    @Override
    public final int compareTo( VersionRequestCondition conditon, HttpServletRequest httpServletRequest ) {
        return conditon.minVersion - minVersion;
    }
}
