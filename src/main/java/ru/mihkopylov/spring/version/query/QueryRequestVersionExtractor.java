package ru.mihkopylov.spring.version.query;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.mihkopylov.spring.version.RequestVersionExtractor;

@AllArgsConstructor
public class QueryRequestVersionExtractor implements RequestVersionExtractor {
    @NonNull
    private final String query;
    @Override
    @NonNull
    public Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.ofNullable( httpServletRequest.getParameter( query ) ).map( Integer :: valueOf );
    }
}
