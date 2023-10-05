package com.reactive.programming;

import reactor.core.publisher.Flux;

public class Example02 {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{
                        "Tom",
                        "Melissa",
                        "Steve",
                        "Megan"
                }).filter(name -> name.length() > 5)
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}