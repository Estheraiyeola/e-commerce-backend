package com.essies.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddToCartRequest {
    private String productName;
    private Long quantity;
    private Long userId;
}
