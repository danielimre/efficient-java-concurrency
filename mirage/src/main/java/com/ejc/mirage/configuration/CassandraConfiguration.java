package com.ejc.mirage.configuration;

import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import org.springframework.boot.autoconfigure.cassandra.DriverConfigLoaderBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfiguration {
    @Bean
    DriverConfigLoaderBuilderCustomizer authProviderCustomizer() {
        return builder -> builder.withString(DefaultDriverOption.AUTH_PROVIDER_CLASS, "com.datastax.oss.driver.internal.core.auth.PlainTextAuthProvider")
                .withString(DefaultDriverOption.LOAD_BALANCING_LOCAL_DATACENTER, "datacenter1");
    }
}