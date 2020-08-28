package ru.mihkopylov.spring.version;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.mvc.versioning")
@Getter
@Setter
public class VersioningConfigurationProperties {
    /**
     * Type of API versioning. Specific version extractor bean is included in context according to the type.
     */
    private VersioningType type;
    /**
     * Prefix for {@link VersioningType#PATH}
     */
    private String pathVersionPrefix = "v";
    /**
     * Header name for {@link VersioningType#HEADER}
     */
    private String header = "version";
    /**
     * Query parameter name for {@link VersioningType#QUERY}
     */
    private String query = "version";
    /**
     * Accept header value format for {@link VersioningType#ACCEPT}. Should have a single regexp group which is used as version.
     */
    private String accept = "application/vnd\\.v(\\d+)\\+json";
    /**
     * Minimal API version
     */
    private int apiMinVersion = 1;
}
