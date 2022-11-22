package com.example.springwebflux.routes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final TestRouter testRouter;

    @Bean
    public RouterFunction<ServerResponse> router() {
        return RouterFunctions.route()
                .nest(RequestPredicates.path("v1"), builder -> builder.path("test", this.testRouter::testRoutes))
                .path("/", this::home)
                .onError(Throwable.class, onException())
                .build();
    }

    private RouterFunction<ServerResponse> home() {
        Map<String, Boolean> data = new HashMap<>();
        data.put("home", true);
        return RouterFunctions.route()
                .GET("", request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(data))
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> onException() {
        return (error, request) -> {
            log.error(String.format("%s : %s - Unexpected error.", request.methodName(), request.path()), error);

            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("field", "00000");
            errorMap.put("message", error.getMessage());

            return ServerResponse
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue(errorMap);
        };
    }
}
