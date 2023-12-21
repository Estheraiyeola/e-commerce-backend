package com.essies.ecommerce.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private String status;
}
