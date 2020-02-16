package com.ejc.mirage.http;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

public interface OkHttpEndpoint {
    @ApiResponse(
        responseCode = "200",
        description = "Successful response with data.",
        headers = {
            @Header(name = "X-Application-Info", description = "Application version information", schema = @Schema(implementation = String.class)),
            @Header(name = "X-Instance-Info", description = "Application instance information", schema = @Schema(implementation = String.class)),
            @Header(name = "X-Deployment-Info", description = "Deployment environment information", schema = @Schema(implementation = String.class))
        }
    )
    @Operation(description = "Returns some data.",
        parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Message-Group-ID", description = "Request correlation id.", schema = @Schema(implementation = String.class)),
            @Parameter(in = ParameterIn.HEADER, name = "X-Session-ID", description = "Request session id.", schema = @Schema(implementation = String.class)),
            @Parameter(in = ParameterIn.HEADER, name = "User-Agent", description = "Client information.", schema = @Schema(implementation = String.class), required = true)
        },
        responses = {
            @ApiResponse(responseCode = "400", description = "Parse error or invalid data in request."),
            @ApiResponse(responseCode = "500", description = "Server side problem.")
        })
    @ResponseBody
    @GetMapping(path = "/ok", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<String> ok();
}
