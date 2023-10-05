package com.reactive.programming;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example05 {
    public static void main(String[] args) {
        Flux<String> flux = Flux.fromArray(new String[]{"a","b","c"});
        Mono<String> mono = Mono.just("f");

        Flux<String> concatFlux = flux.concatWith(mono);
        concatFlux.subscribe(element -> System.out.println(element + " "));
    }
}
