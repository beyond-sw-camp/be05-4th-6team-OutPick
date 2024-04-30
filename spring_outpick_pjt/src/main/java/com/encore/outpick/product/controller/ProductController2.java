package com.encore.outpick.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/product")
public class ProductController2 {
    @PostMapping("/write")
    public String addProduct(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @PostMapping("/{productNo}")
    public String modifyProduct(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @DeleteMapping("/{productNo}")
    public String deleteProduct(@RequestBody String entity) {

        return entity;
    }

    @GetMapping("/{productName}")
    public String findByName(@RequestParam String param) {
        return new String();
    }
    
}
