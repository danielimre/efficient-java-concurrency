package com.ejc.mirage.http;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Provides endpoints for terminal operations which are not calling to another service.
 */
@RestController
@RequestMapping("/terminal")
public class TerminalOperationsController implements TerminalOperationsEndpoint {
    @Override
    public Mono<Integer> getId(@PathVariable Integer id) {
        return Mono.just(Optional.ofNullable(id).orElse(1));
    }
}
