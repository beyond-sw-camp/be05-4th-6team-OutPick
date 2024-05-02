package com.encore.outpick.product.mapper;

import com.encore.outpick.product.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDTO selectProductById(long productId);

    List<ProductDTO> selectAllProducts();

    List<ProductDTO> sortAllProducts(String sortBy);

    List<ProductDTO> selectProductByCategoryId(String categoryId);
}
