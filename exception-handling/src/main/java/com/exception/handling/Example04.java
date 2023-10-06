package com.exception.handling;

import reactor.core.publisher.Flux;

public class Example04 {
    public static void main(String[] args) {
        Flux.just(2,0,7,10,8,12,22,24)
                .map(element -> {
                    if(element == 8){
                        throw new RuntimeException("Exception ocurred!");
                    }
                    return element;
                })
                .onErrorMap((ex) ->{
            System.out.println("Exception : " + ex);
            return new CustomException(ex.getMessage(), ex);
        })
                .log()
                .subscribe();
    }
}