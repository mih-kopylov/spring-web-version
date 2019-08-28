package ru.mihkopylov.spring.version.query;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.VersionRequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.VersioningConfiguration;

@Configuration
@ConditionalOnProperty(value = "spring.mvc.versioning.type", havingValue = "QUERY")
@AllArgsConstructor
public class QueryVersionWebConfiguration extends WebMvcConfigurationSupport {
    @NonNull
    private final VersioningConfiguration configuration;

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping( configuration.getApiMinVersion(),
                new QueryRequestVersionExtractor( configuration.getQuery() ) );
    }
}
