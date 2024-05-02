package com.encore.outpick.product.domain;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductDAO {
    private Long product_id;
    private String category_id;
    private String name;
    private Long price;
    private Long stock;
    private String content;
    private Date incomming_date;
    private String supplier;
    private String storage;

}
