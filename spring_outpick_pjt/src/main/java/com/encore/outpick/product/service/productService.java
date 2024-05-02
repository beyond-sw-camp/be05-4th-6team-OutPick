package com.encore.outpick.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.outpick.product.domain.ProductDAO;
import com.encore.outpick.product.mapper.ProductMapper;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public void addProduct(ProductDAO params) {
        System.out.println("debug >>> ProductService addProduct");
        productMapper.addProduct(params);
    }

    public void modifyProduct(ProductDAO params) {
        System.out.println("debug >>> ProductService modifyProduct");
        productMapper.modifyProduct(params);
    }

    public void deleteProduct(Long productId) {
        System.out.println("debug >>> ProductService deleteProduct");
        productMapper.deleteProduct(productId);
    }
    
    public List<ProductDAO> findByName(String name) {
        System.out.println("debug >>> ProductService findByName");
        return productMapper.findByName(name);
    }
}
