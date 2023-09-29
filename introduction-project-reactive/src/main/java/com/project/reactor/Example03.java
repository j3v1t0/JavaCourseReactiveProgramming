package com.project.reactor;

import reactor.core.publisher.Mono;

public class Example03 {
    public static void main(String[] args) {
        Mono<String> mono = Mono.fromSupplier(
                () -> {
                    throw new RuntimeException("Exception Occurred");
                }
        );
        mono.subscribe(
                data -> System.out.println(data), //onNext
                err -> System.out.println(err), //onError
                () -> System.out.printf("Completed") //onComplete
        );
    }
}
