package com.reactive.example.controller;

import com.reactive.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/list")
    public String productList(Model model){
        //Reactive Variable
        IReactiveDataDriverContextVariable reactiveList =
                new ReactiveDataDriverContextVariable(productRepository.buscarTodos(),1);
        model.addAttribute("productList",reactiveList);
        return "list";
    }
}
