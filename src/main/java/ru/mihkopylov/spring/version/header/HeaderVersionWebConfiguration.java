package ru.mihkopylov.spring.version.header;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.VersionRequestMappingHandlerMapping;

@Configuration
@ConditionalOnProperty(value = "spring.mvc.versioning.type", havingValue = "HEADER")
@AllArgsConstructor
public class HeaderVersionWebConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping( new HeaderRequestVersionExtractor() );
    }
}
