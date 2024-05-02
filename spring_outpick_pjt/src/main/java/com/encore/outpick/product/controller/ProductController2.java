package com.encore.outpick.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.outpick.product.domain.ProductDAO;
import com.encore.outpick.product.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/product")
public class ProductController2 {
    @Autowired
    private ProductService productService;

    @PostMapping("/write")
    public String addProduct(@RequestBody ProductDAO params) {
        System.out.println("debug >>> ProductController POST :/write");
        System.out.println("debug >>> request params, " + params);
        productService.addProduct(params);
        return null;
    }
    
    @PostMapping("/modify/{productId}")
    public String modifyProduct(@RequestBody ProductDAO product, @PathVariable("productId") Long productId) {
        System.out.println("debug >>> ProductController POST :/modify/{productId}");
        System.out.println("debug >>> request params, " + product);
        product.setProduct_id(productId);
        productService.modifyProduct(product);
        return null;
    }
    
    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        System.out.println("debug >>> ProductController DELETE :/delete/{productId}");
        System.out.println("debug >>> request params, " + productId);
        productService.deleteProduct(productId);
        return null;
    }

    @GetMapping("/find/{Name}")
    // public List<ProductDAO> findByName(@RequestParam String name) {
    //     System.out.println("debug >>> ProductController GET :/find/{name}");
    //     System.out.println("debug >>> name" + name);
    //     return productService.findByName(name);
    // }
    public List<ProductDAO> findByName(@PathVariable("Name") String name) {
        System.out.println("debug >>> ProductController GET :/find/{name}");
        System.out.println("debug >>> name" + name);
        return productService.findByName(name);
    }
    
}
