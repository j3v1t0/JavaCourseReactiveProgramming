package com.project.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {
    public static void main(String[] args) {

        List<Integer> elementsFromMono = new ArrayList<>();
        List<Integer> elementsFromFlux = new ArrayList<>();
        //Create a Mono
        Mono<Integer> mono = Mono.just(121);

        //Create a Flux
        Flux<Integer> flux = Flux.just(12,14,9,11,12,20,23,8,5,6,7);

        // Suscribe to Mono
        mono.subscribe(elementsFromMono::add);

        //Suscribe to Flux
        flux.subscribe(elementsFromFlux::add);

        System.out.println(elementsFromMono);
        System.out.println(elementsFromFlux);
    }
}