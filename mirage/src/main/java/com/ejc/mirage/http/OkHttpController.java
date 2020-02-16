package com.ejc.mirage.http;

import java.time.Duration;

import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController //needed for openapi to work
public class OkHttpController implements OkHttpEndpoint {
    @Override
    public Mono<String> ok() {
        return Mono.just("data").delayElement(Duration.ofMillis(100));
    }
}
