package com.reactive.programming;

import reactor.core.publisher.Flux;

public class Example04 {
    public static void main(String[] args) {
        Flux<String> firstFlux = Flux.fromArray(new String[]{"a","b","c"});
        Flux<String> secondFlux = Flux.fromArray(new String[]{"d","e","f"});

        Flux<String> concatFlux = Flux.concat(firstFlux,secondFlux);
        concatFlux.subscribe(element -> System.out.println(element + " "));
    }
}
