package com.encore.outpick.product.controller;

import com.encore.outpick.product.domain.ProductDTO;
import com.encore.outpick.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable("productId") long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/list")
    public List<ProductDTO> getAllProductList() {
        return productService.getAllProducts();
    }

    @GetMapping("/sort/{sortBy}")
    public List<ProductDTO> getProductSort(@PathVariable("sortBy") String sortBy) {
        System.out.println(sortBy);
        return productService.getSortProduct(sortBy);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategoryId(@PathVariable("categoryId") String categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    @PostMapping("/write")
    public void addProduct(@RequestBody ProductDTO params) {
        System.out.println("debug >>> ProductController POST :/write");
        System.out.println("debug >>> request params, " + params);
        productService.addProduct(params);
    }

    @PostMapping("/modify/{productId}")
    public void modifyProduct(@RequestBody ProductDTO product, @PathVariable("productId") long productId) {
        System.out.println("debug >>> ProductController POST :/modify/{productId}");
        System.out.println("debug >>> request params, " + product);
        product.setProductId(productId);
        productService.modifyProduct(product);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        System.out.println("debug >>> ProductController DELETE :/delete/{productId}");
        System.out.println("debug >>> request params, " + productId);
        productService.deleteProduct(productId);
    }

    @GetMapping("/find/{Name}")
    public List<ProductDTO> findByName(@PathVariable("Name") String name) {
        System.out.println("debug >>> ProductController GET :/find/{name}");
        System.out.println("debug >>> name" + name);
        return productService.findByName(name);
    }

}
