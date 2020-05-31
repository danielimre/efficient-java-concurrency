package com.ejc.mirage.configuration;

import java.util.Arrays;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTags;
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTagsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class MetricsConfiguration implements WebFluxTagsProvider {
    @Value("${application.shortname}")
    private String appShortName;
    @Value("${application.instance}")
    private String appInstance;
    @Value("${application.environment}")
    private String appEnvironment;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
            .commonTags("application", appShortName, "environment", appEnvironment, "node", appInstance);
    }

    @Override
    public Iterable<Tag> httpRequestTags(final ServerWebExchange exchange, final Throwable exception) {
        return Arrays.asList(WebFluxTags.uri(exchange), WebFluxTags.status(exchange));
    }
}
