package com.reactive.api.rest.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class HelloRouter {
    @Bean
    public RouterFunction<ServerResponse> functionalRoutes(HelloHandler helloHandler){
        return RouterFunctions
                .route(RequestPredicates.GET("/functional/mono"), helloHandler::showMessageMono)
                        .andRoute(RequestPredicates.GET("/functional/flux"),helloHandler::showMessageFlux);
    }
}
