package com.reactive.programming;

import reactor.core.publisher.Flux;

public class Example07 {
    public static void main(String[] args) {
        Flux<Integer> flux1 = Flux.just(1,2,3,4,5);
        Flux<Integer> flux2 = Flux.just(4,5,6);

        //1st Form
        Flux.zip(flux1,flux2,(first,second) -> first + second).subscribe(System.out::println);
        //2nd Form
        flux1.zipWith(flux2,(first,second) -> first + second).subscribe(System.out::println);
    }
}
