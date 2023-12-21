package com.essies.ecommerce.dto.response;

import com.essies.ecommerce.data.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class AllProductsInA_CategoryResponse {
    private List<Product> productList;
    private Long total;
}
