package com.reactive.programming;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublishers {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> netFlux = Flux.fromStream(HotPublishers::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .share();

        netFlux.subscribe(part -> System.out.println("Suscriber 1: " + part));
        Thread.sleep(5000);
        netFlux.subscribe(part -> System.out.println("Suscriber 2: " + part));
        Thread.sleep(6000);
    }
    private static Stream<String> getVideo(){
        System.out.println("Request for the video");
        return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
    }
}
