package com.reactive.example.service;

import com.reactive.example.models.Product;
import com.reactive.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> findAll(){
        Flux<Product> flux1 = productRepository.findAll();
        Flux<Product> flux2 = productRepository.findOthers();

        return Flux.merge(flux1,flux2);
    }
}
