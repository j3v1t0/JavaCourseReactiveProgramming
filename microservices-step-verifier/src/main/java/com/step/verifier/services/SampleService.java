package com.step.verifier.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SampleService {
    public Mono<String> findOne(){
        return Mono.just("Hi!");
    }

    public Flux<String> findAll(){
        return Flux.just("Hi!", "How", "Are", "You");
    }

    public Flux<String> findAllSlowly(){
        return Flux.just("Hi!", "How", "Are", "You")
                .delaySequence(Duration.ofSeconds(10));
    }
}
