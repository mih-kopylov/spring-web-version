package ru.mihkopylov.spring.version.query;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import ru.mihkopylov.spring.version.AbstractVersionRequestCondition;

public class QueryVersionRequestCondition extends AbstractVersionRequestCondition<QueryVersionRequestCondition> {
    public QueryVersionRequestCondition( int minVersion, int maxVersion ) {
        super( minVersion, maxVersion );
    }

    @Override
    protected @NonNull Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.ofNullable( httpServletRequest.getParameter( "version" ) ).map( Integer :: valueOf );
    }
}
