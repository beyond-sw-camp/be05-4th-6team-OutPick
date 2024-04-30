package com.encore.outpick.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.encore.outpick.product.domain.ProductDTO;

@Mapper
public interface ProductMapper {
    public void addProduct(ProductDTO params);
    public void modifyProduct(ProductDTO params);
    public void deleteProduct(ProductDTO params);
    public List<ProductDTO> findByName(ProductDTO params);
}
