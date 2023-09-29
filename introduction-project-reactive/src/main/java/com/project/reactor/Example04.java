package com.project.reactor;

import reactor.core.publisher.Flux;

public class Example04 {
    public static void main(String[] args) {
        Flux<String> flux = Flux.fromArray(new String[]{
                "Data1",
                "Data2",
                "Data3"
        });
        flux.subscribe(
                System.out::println
        );

        //2nd Form
        flux.doOnNext(
                System.out::println
        ).subscribe();
    }
}
