package com.essies.ecommerce.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class CreatedInventoryResponse {
    private Long productId;
    private Long stockQuantity;
    private Date lastRestockedDate;
    private String status;
}
