package com.reactive.programming;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Example01 {
    public static void main(String[] args) {
        Flux<String> cities = Flux.fromIterable(
                new ArrayList<>(Arrays.asList("New York", "London","Paris","Toronto","Rome"))
        );
        cities.log().subscribe();
    }
}