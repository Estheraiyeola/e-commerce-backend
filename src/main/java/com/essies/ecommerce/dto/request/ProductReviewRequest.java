package com.essies.ecommerce.dto.request;

import com.essies.ecommerce.data.model.Rating;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductReviewRequest {
    private String productName;
    private String message;
    private Rating rating;
}
