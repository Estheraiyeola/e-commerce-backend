package com.essies.ecommerce.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class AddedProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private String imageUrl;
    private Long categoryId;
    private String status;
}
