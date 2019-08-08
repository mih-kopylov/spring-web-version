package ru.mihkopylov.spring.version.header;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import ru.mihkopylov.spring.version.RequestVersionExtractor;

public class HeaderRequestVersionExtractor implements RequestVersionExtractor {
    @Override
    @NonNull
    public Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.ofNullable( httpServletRequest.getHeader( "version" ) ).map( Integer :: valueOf );
    }
}
