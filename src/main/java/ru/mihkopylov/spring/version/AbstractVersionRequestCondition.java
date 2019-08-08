package ru.mihkopylov.spring.version;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

@AllArgsConstructor
@Getter
public abstract class AbstractVersionRequestCondition<T extends AbstractVersionRequestCondition<T>>
        implements RequestCondition<T> {
    private final int minVersion;
    private final int maxVersion;

    @Override
    public final T combine( T condition ) {
        return condition;
    }

    @Override
    public final T getMatchingCondition( HttpServletRequest httpServletRequest ) {
        Optional<Integer> requestVersion = getRequestVersion( httpServletRequest );
        if (requestVersion.isEmpty()) {
            return null;
        }
        if (requestVersion.get() < minVersion || requestVersion.get() > maxVersion) {
            return null;
        }
        return (T) this;
    }

    @NonNull
    protected abstract Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest );

    @Override
    public final int compareTo( T conditon, HttpServletRequest httpServletRequest ) {
        return conditon.getMinVersion() - minVersion;
    }
}
