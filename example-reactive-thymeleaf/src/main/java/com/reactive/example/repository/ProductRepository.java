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

    static {
        list.add(new Product(1,200,"computer"));
        list.add(new Product(2,300,"tablet"));
        list.add(new Product(3,100,"earphones"));
    }

    public Flux<Product> buscarTodos(){
        return Flux.fromIterable(list).delayElements(Duration.ofSeconds(3));
    }

}
