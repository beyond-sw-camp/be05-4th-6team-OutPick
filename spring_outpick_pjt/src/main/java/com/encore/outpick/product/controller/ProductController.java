package com.encore.outpick.product.controller;

import com.encore.outpick.product.domain.ProductDTO;
import com.encore.outpick.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
