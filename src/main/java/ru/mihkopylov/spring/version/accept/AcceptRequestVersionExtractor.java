package ru.mihkopylov.spring.version.accept;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import ru.mihkopylov.spring.version.RequestVersionExtractor;

@AllArgsConstructor
@Slf4j
public class AcceptRequestVersionExtractor implements RequestVersionExtractor {
    @NonNull
    private final String acceptTemplate;

    @Override
    @NonNull
    public Optional<Integer> getRequestVersion( @NonNull HttpServletRequest httpServletRequest ) {
        return Optional.ofNullable( httpServletRequest.getHeader( HttpHeaders.ACCEPT ) )
                .flatMap( this :: extractVersionPart )
                .map( Integer :: valueOf );
    }

    @NonNull
    private Optional<String> extractVersionPart( @NonNull String accept ) {
        Pattern pattern = Pattern.compile( acceptTemplate );
        Matcher matcher = pattern.matcher( accept );
        if (!matcher.find()) {
            log.debug( "Accept header {} does not match acceptTemplate {}", accept, acceptTemplate );
            return Optional.empty();
        }
        if (matcher.groupCount() < 1) {
            log.debug( "acceptTemplate {} should declare a group using parenthesis, but it does not", acceptTemplate );
            return Optional.empty();
        }
        return Optional.ofNullable( matcher.group( 1 ) );
    }
}
