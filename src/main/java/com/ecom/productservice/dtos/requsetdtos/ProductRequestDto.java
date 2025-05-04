package com.ecom.productservice.dtos.requsetdtos;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private String category;
    private Boolean active;
    private String imageUrl;
    private Integer quantity;
}
