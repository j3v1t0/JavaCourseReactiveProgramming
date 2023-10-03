package com.reactive.programming;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class Example02 {
    public static void main(String[] args) {
        //Generate numbers from 1 to 100
        Flux<Integer> integerFlux = Flux.range(1, 100).log();
        integerFlux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(10);
            }

            @Override
            protected void hookOnNext(Integer value) {
                if(value == 5){
                    cancel();
                }
            }
        });
    }
}
