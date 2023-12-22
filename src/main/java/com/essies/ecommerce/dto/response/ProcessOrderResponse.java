package com.essies.ecommerce.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProcessOrderResponse {
    private String message;
    private LocalDateTime dateProcessed;
}
