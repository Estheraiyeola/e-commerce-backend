package com.essies.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class CreateInventoryRequest {
    private Long productId;
    private Long stockQuantity;
    private Date lastRestockedDate;
}
