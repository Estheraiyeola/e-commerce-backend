package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Category;
import com.essies.ecommerce.dto.request.CreateCategoryRequest;
import com.essies.ecommerce.dto.response.CreatedCategoryResponse;

public interface CategoryService {
    CreatedCategoryResponse createCategory(CreateCategoryRequest categoryRequest);

    void deleteAll();

    Category findByName(String category);
}
