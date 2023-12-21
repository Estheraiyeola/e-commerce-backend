package com.essies.ecommerce.dto.response;

import com.essies.ecommerce.data.model.Cart;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RemovedProductsCartResponse {
    private Cart orderItemList;
}
