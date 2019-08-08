package ru.mihkopylov.spring.version.query;

import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class QueryVersionRequestCondition implements RequestCondition<QueryVersionRequestCondition> {
    private final int minVersion;
    private final int maxVersion;

    @Override
    public QueryVersionRequestCondition combine(
            QueryVersionRequestCondition queryVersionRequestCondition) {
        return queryVersionRequestCondition;
    }

    @Override
    public QueryVersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        final String requestVersionParameter = httpServletRequest.getParameter("version");
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
    public int compareTo(QueryVersionRequestCondition queryVersionRequestCondition, HttpServletRequest httpServletRequest) {
        return queryVersionRequestCondition.minVersion - minVersion;
    }
}
