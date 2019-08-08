package ru.mihkopylov.spring.version.header;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import ru.mihkopylov.spring.version.AbstractVersionRequestCondition;

public class HeaderVersionRequestCondition extends AbstractVersionRequestCondition<HeaderVersionRequestCondition> {
    public HeaderVersionRequestCondition( int minVersion, int maxVersion ) {
        super( minVersion, maxVersion );
    }

    @Override
    @NonNull
    protected Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.ofNullable( httpServletRequest.getHeader( "version" ) ).map( Integer :: valueOf );
    }
}
