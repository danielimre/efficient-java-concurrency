package com.ejc.mirage.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

public interface TerminalOperationsEndpoint {

    @ResponseBody
    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<Integer> getId(@PathVariable Integer id);
}
