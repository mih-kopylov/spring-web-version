package ru.mihkopylov.spring.version.header;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.mihkopylov.spring.version.RequestVersionExtractor;

@AllArgsConstructor
public class HeaderRequestVersionExtractor implements RequestVersionExtractor {
    @NonNull
    private final String header;

    @Override
    @NonNull
    public Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.ofNullable( httpServletRequest.getHeader( header ) ).map( Integer :: valueOf );
    }
}
