package com.encore.outpick.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.encore.outpick.product.domain.ProductDAO;

@Mapper
public interface ProductMapper {
    public void addProduct(ProductDAO params);
    public void modifyProduct(ProductDAO product);
    public void deleteProduct(Long productId);
    public List<ProductDAO> findByName(String name);
}
