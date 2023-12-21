package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Category;
import com.essies.ecommerce.data.repository.CategoryRepository;
import com.essies.ecommerce.dto.request.CreateCategoryRequest;
import com.essies.ecommerce.dto.response.CreatedCategoryResponse;
import com.essies.ecommerce.exception.CategoryAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CreatedCategoryResponse createCategory(CreateCategoryRequest categoryRequest) {
        Category foundCategory = categoryRepository.findCategoryByName(categoryRequest.getName());
        if(foundCategory == null){
            Category category = new Category();
            category.setName(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());
            categoryRepository.save(category);

            CreatedCategoryResponse createdCategoryResponse = new CreatedCategoryResponse();
            createdCategoryResponse.setName(category.getName());
            createdCategoryResponse.setDescription(category.getDescription());
            createdCategoryResponse.setStatus("Successful");
            createdCategoryResponse.setId(category.getId());
            return createdCategoryResponse;
        }
        throw new CategoryAlreadyExistsException("Category Already Exists!");
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public Category findByName(String category) {
        return categoryRepository.findCategoryByName(category);
    }
}
