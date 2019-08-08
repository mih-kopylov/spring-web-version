package ru.mihkopylov.spring.version.path;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import ru.mihkopylov.spring.version.RequestVersionExtractor;

public class PathRequestVersionExtractor implements RequestVersionExtractor {
    @Override
    @NonNull
    public Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.of( httpServletRequest.getRequestURI() )
                .filter( uri -> uri.startsWith( "/v" ) )
                .stream()
                .flatMap( uri -> Arrays.stream( uri.split( "/" ) ) )
                .map( String :: trim )
                .map( part -> part.isBlank() ? null : part )
                .filter( Objects :: nonNull )
                .filter( part -> part.length() > 1 )
                .map( part -> part.substring( 1 ) )
                .findFirst()
                .map( Integer :: valueOf );
    }
}
