package ru.mihkopylov.spring.version;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;

/**
 * Purpose of this class is to find which API version is requested
 */
public interface RequestVersionExtractor {
    @NonNull
    Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest );
}
