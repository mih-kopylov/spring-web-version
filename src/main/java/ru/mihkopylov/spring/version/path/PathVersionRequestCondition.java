package ru.mihkopylov.spring.version.path;

import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class PathVersionRequestCondition implements RequestCondition<PathVersionRequestCondition> {
    private final int minVersion;
    private final int maxVersion;

    @Override
    public PathVersionRequestCondition combine(PathVersionRequestCondition pathVersionRequestCondition) {
        return pathVersionRequestCondition;
    }

    @Override
    public PathVersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        final String uri = httpServletRequest.getRequestURI();
        if (!uri.startsWith("/v")) {
            return null;
        }

        final String versionUriPart = Arrays.stream(uri.split("/"))
                .map(String::trim)
                .map(o -> o.isBlank() ? null : o)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        if (isNull(versionUriPart)) {
            return null;
        }
        final Integer requestVersion = Integer.valueOf(versionUriPart.substring(1));
        if (requestVersion < minVersion || requestVersion > maxVersion) {
            return null;
        }
        return this;
    }

    @Override
    public int compareTo(PathVersionRequestCondition pathVersionRequestCondition,
            HttpServletRequest httpServletRequest) {
        return pathVersionRequestCondition.minVersion - minVersion;
    }
}
