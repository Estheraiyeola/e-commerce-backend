package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.dto.request.AddProductRequest;
import com.essies.ecommerce.dto.request.ProductReviewRequest;
import com.essies.ecommerce.dto.response.AddedProductResponse;
import com.essies.ecommerce.dto.response.AllProductsInA_CategoryResponse;
import com.essies.ecommerce.dto.response.ProductReviewResponse;
import com.essies.ecommerce.exception.ProductDoesNotExistException;

import java.util.List;

public interface ProductService {
    AddedProductResponse addProduct(AddProductRequest addProductRequest);

    void deleteAll();

    AllProductsInA_CategoryResponse getProductsInA_Category(String category);

    void removeProduct(String name);

    Product findProduct(String productName);


    List<Product> findProductsByCategoryName(String categoryName);

    ProductReviewResponse addReview(ProductReviewRequest productReviewRequest, Long userId);
}
