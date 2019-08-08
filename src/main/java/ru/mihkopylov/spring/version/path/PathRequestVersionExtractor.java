package ru.mihkopylov.spring.version.path;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.mihkopylov.spring.version.RequestVersionExtractor;

@AllArgsConstructor
public class PathRequestVersionExtractor implements RequestVersionExtractor {
    @NonNull
    private final String pathVersionPrefix;

    @Override
    @NonNull
    public Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.of( httpServletRequest.getRequestURI() )
                .filter( uri -> uri.startsWith( "/" + pathVersionPrefix ) )
                .stream()
                .flatMap( uri -> Arrays.stream( uri.split( "/" ) ) )
                .map( String :: trim )
                .map( part -> part.isBlank() ? null : part )
                .filter( Objects :: nonNull )
                .filter( part -> part.length() > pathVersionPrefix.length() )
                .map( part -> part.substring( pathVersionPrefix.length() ) )
                .findFirst()
                .map( Integer :: valueOf );
    }
}
