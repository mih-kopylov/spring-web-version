package ru.mihkopylov.spring.version.query;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import ru.mihkopylov.spring.version.RequestVersionExtractor;

public class QueryRequestVersionExtractor implements RequestVersionExtractor {
    @Override
    @NonNull
    public Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.ofNullable( httpServletRequest.getParameter( "version" ) ).map( Integer :: valueOf );
    }
}
