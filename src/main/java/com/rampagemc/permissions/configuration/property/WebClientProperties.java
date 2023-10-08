package com.rampagemc.permissions.configuration.property;

import com.rampagemc.backlib.configuration.property.RestConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rampagemc.luckperms")
public class WebClientProperties {

    private URL baseUrl;

    @Configuration
    static class WebClientConfiguration {

        @Bean
        public WebClient permissionsServiceWebClient(WebClient.Builder defaultWebClient, WebClientProperties webClientProperties) {
            return defaultWebClient
                    .baseUrl(webClientProperties.getBaseUrl()
                            + RestConfiguration.PRIVATE
                            + "/luckperms")
                    .build();
        }
    }
}
