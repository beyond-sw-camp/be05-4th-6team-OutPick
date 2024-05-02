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
}
