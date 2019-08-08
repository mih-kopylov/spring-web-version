package ru.mihkopylov.spring.version;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.mvc.versioning")
@Getter
@Setter
public class VersioningConfiguration {
    private VersioningType type;
    private String pathVersionPrefix = "v";
    private String header = "version";
    private String query = "version";
    private String accept = "application/vnd\\.v(\\d+)\\+json";
}
