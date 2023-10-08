package com.rampagemc.permissions.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rampagemc.permissions")
public class MigrationProperties {

    /**
     * Directory path used to list of all its changelog files from classpath.
     */
    private String changelogPath;
}
