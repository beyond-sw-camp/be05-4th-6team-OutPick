package com.encore.outpick.product.service;

import com.encore.outpick.product.domain.ProductDTO;
import com.encore.outpick.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public ProductDTO getProductById(long productId) {
        return productMapper.selectProductById(productId);
    }

    public List<ProductDTO> getAllProducts() {
        return productMapper.selectAllProducts();
    }

    public List<ProductDTO> getSortProduct(String sortBy){
        return productMapper.sortAllProducts(sortBy);
    }

    public List<ProductDTO> getProductByCategoryId(String categoryId) {
        return productMapper.selectProductByCategoryId(categoryId);
    }

    ////////////////////////////////////////////////////////////////////////////////

    public void addProduct(ProductDTO params) {
        System.out.println("debug >>> ProductService addProduct");
        productMapper.addProduct(params);
    }

    public void modifyProduct(ProductDTO params) {
        System.out.println("debug >>> ProductService modifyProduct");
        productMapper.modifyProduct(params);
    }

    public void deleteProduct(Long productId) {
        System.out.println("debug >>> ProductService deleteProduct");
        productMapper.deleteProduct(productId);
    }

    public List<ProductDTO> findByName(String name) {
        System.out.println("debug >>> ProductService findByName");
        return productMapper.findByName(name);
    }

}
