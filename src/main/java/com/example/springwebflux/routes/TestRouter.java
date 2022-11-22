package com.example.springwebflux.routes;

import com.example.springwebflux.handler.TestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class TestRouter {

    private final TestHandler testHandler;

    @Bean
    public RouterFunction<ServerResponse> testRoutes() {
        return RouterFunctions.route()
                .GET("", this.testHandler::getTest)
                .build();
    }
}
