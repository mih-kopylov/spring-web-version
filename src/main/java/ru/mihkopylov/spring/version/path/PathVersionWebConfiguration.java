package ru.mihkopylov.spring.version.path;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.VersionRequestMappingHandlerMapping;
import ru.mihkopylov.spring.version.VersioningConfigurationProperties;

@Configuration
@ConditionalOnProperty(value = "spring.mvc.versioning.type", havingValue = "path")
@EnableConfigurationProperties(VersioningConfigurationProperties.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class PathVersionWebConfiguration extends DelegatingWebMvcConfiguration {
    @NonNull
    private final VersioningConfigurationProperties configuration;

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping( configuration.getApiMinVersion(),
                new PathRequestVersionExtractor( configuration.getPathVersionPrefix() ) );
    }

    @Override
    protected void configurePathMatch( PathMatchConfigurer configurer ) {
        configurer.setPathMatcher( new VersionPathMatcher( configuration.getPathVersionPrefix() ) );
    }
}
