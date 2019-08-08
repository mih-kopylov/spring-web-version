package ru.mihkopylov.spring.version.path;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ConditionalOnProperty(value = "spring.mvc.versioning.type", havingValue = "PATH")
@AllArgsConstructor
public class PathVersionWebConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new PathVersionRequestMappingHandlerMapping();
    }

    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPathMatcher(new VersionPathMatcher());
    }
}
