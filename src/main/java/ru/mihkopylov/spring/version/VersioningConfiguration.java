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
    private boolean enabled;
    private VersioningType type;
}
