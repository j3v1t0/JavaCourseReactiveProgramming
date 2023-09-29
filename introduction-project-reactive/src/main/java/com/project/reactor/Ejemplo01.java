package com.project.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo01 {
    public static void main(String[] args) {
        //Create a Mono
        Mono<Integer> mono = Mono.just(121);

        //Create a Flux
        Flux<Integer> flux = Flux.just(12,14,9,11,12,20,23,8,5,6,7);
    }
}