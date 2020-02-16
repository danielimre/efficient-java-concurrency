package com.ejc.mirage.configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.net.URI;
import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/routed-ok")
                .and(accept(APPLICATION_JSON)),
                serverRequest -> ServerResponse.ok().contentType(APPLICATION_JSON).body(Mono.just("data").delayElement(Duration.ofMillis(100)), String.class))
            .filter((request, next) -> {
                LOG.info("{} request to {}", request.methodName(), request.path());
                return next.handle(request);
            });
    }

    @Bean
    RouterFunction<ServerResponse> indexRouterFunction() {
        return route(GET("/"), req -> ServerResponse.temporaryRedirect(URI.create("/api-doc-ui")).build());
    }
}
