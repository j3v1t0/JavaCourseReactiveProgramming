package com.reactive.example.repository;

import com.reactive.example.models.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static List<Product> list = new ArrayList<>();
    private static List<Product> list2 = new ArrayList<>();

    static {
        list.add(new Product(1,200,"computer"));
        list.add(new Product(2,300,"tablet"));
        list.add(new Product(3,100,"earphones"));

        list2.add(new Product(4,200,"cellphone"));
        list2.add(new Product(5,300,"keyboard"));
        list2.add(new Product(6,100,"mouse"));
    }

    public Flux<Product> findAll(){
        return Flux.fromIterable(list).delayElements(Duration.ofSeconds(3));
    }

    public Flux<Product> findOthers(){
        return Flux.fromIterable(list2).delayElements(Duration.ofSeconds(3));
    }

}
