package com.example.springwebflux.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestHandler {

    public Mono<ServerResponse> getTest(ServerRequest serverRequest) {
        Map<String, String> data = new HashMap<>();
        data.put("test", serverRequest.path());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(data);
    }
}
