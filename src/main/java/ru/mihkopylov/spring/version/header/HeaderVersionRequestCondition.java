package ru.mihkopylov.spring.version.header;

import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class HeaderVersionRequestCondition implements RequestCondition<HeaderVersionRequestCondition> {
    private final int minVersion;
    private final int maxVersion;

    @Override
    public HeaderVersionRequestCondition combine(HeaderVersionRequestCondition headerVersionRequestCondition) {
        return headerVersionRequestCondition;
    }

    @Override
    public HeaderVersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        final String requestVersionParameter = httpServletRequest.getHeader("version");
        if (isNull(requestVersionParameter)) {
            return null;
        }
        final Integer requestVersion = Integer.valueOf(requestVersionParameter);
        if (requestVersion < minVersion || requestVersion > maxVersion) {
            return null;
        }
        return this;
    }

    @Override
    public int compareTo(HeaderVersionRequestCondition headerVersionRequestCondition,
            HttpServletRequest httpServletRequest) {
        return headerVersionRequestCondition.minVersion - minVersion;
    }
}
