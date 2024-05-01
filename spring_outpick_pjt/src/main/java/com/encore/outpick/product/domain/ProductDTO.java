package com.encore.outpick.product.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
public class ProductDTO {
    private Long productId;
    private String categoryId;
    private String name;
    private Long price;
    private Long stock;
    private String content;
    private Date incommingDate;
    private String supplier;
    private String storage;
}