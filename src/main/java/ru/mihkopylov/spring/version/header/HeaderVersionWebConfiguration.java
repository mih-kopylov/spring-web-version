package ru.mihkopylov.spring.version.header;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.VersionRequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.VersioningConfiguration;

@Configuration
@ConditionalOnProperty(value = "spring.mvc.versioning.type", havingValue = "HEADER")
@EnableConfigurationProperties(VersioningConfiguration.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class HeaderVersionWebConfiguration extends DelegatingWebMvcConfiguration {
    @NonNull
    private final VersioningConfiguration configuration;

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping( configuration.getApiMinVersion(),
                new HeaderRequestVersionExtractor( configuration.getHeader() ) );
    }
}
