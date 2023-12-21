package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Category;
import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.data.model.Review;
import com.essies.ecommerce.data.repository.ProductRepository;
import com.essies.ecommerce.dto.request.AddProductRequest;
import com.essies.ecommerce.dto.request.CreateInventoryRequest;
import com.essies.ecommerce.dto.request.ProductReviewRequest;
import com.essies.ecommerce.dto.response.AddedProductResponse;
import com.essies.ecommerce.dto.response.AllProductsInA_CategoryResponse;
import com.essies.ecommerce.dto.response.CreatedInventoryResponse;
import com.essies.ecommerce.dto.response.ProductReviewResponse;
import com.essies.ecommerce.exception.ProductAlreadyExistsException;
import com.essies.ecommerce.exception.ProductDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ReviewService reviewService;
    @Override
    public AddedProductResponse addProduct(AddProductRequest addProductRequest) {
        Product foundProduct = productRepository.findByName(addProductRequest.getName());
        if (foundProduct == null){
            Product product = new Product();
            product.setName(addProductRequest.getName());
            product.setDescription(addProductRequest.getDescription());
            product.setPrice(addProductRequest.getPrice());
            product.setQuantity(addProductRequest.getQuantity());
            product.setCategoryId(addProductRequest.getCategoryId());
            product.setImageUrl(addProductRequest.getImageUrl());
            productRepository.save(product);

            CreateInventoryRequest createInventoryRequest = new CreateInventoryRequest();
            createInventoryRequest.setProductId(product.getId());
            createInventoryRequest.setStockQuantity(product.getQuantity());
            createInventoryRequest.setLastRestockedDate(Date.from(Instant.now()));
            CreatedInventoryResponse createdInventoryResponse = inventoryService.addProductToInventory(createInventoryRequest);

            AddedProductResponse response = new AddedProductResponse();
            response.setName(product.getName());
            response.setDescription(product.getDescription());
            response.setPrice(product.getPrice());
            response.setQuantity(product.getQuantity());
            response.setCategoryId(product.getCategoryId());
            response.setImageUrl(product.getImageUrl());
            response.setStatus("Successful!!");
            response.setId(product.getId());
            return response;
        }
        throw new ProductAlreadyExistsException("Product Already Exists!!!");
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public AllProductsInA_CategoryResponse getProductsInA_Category(String category) {
        AllProductsInA_CategoryResponse products = new AllProductsInA_CategoryResponse();
        List<Product> tempProductList = new ArrayList<>();
        Category foundCategory = categoryService.findByName(category);
        for (Product foundProduct:productRepository.findAll()) {
            if (foundProduct.getCategoryId() == foundCategory.getId()){
                tempProductList.add(foundProduct);
            }
        }
        products.setProductList(tempProductList);
        products.setTotal((long) tempProductList.size());
        return products;
    }

    @Override
    public void removeProduct(String name) {
        productRepository.deleteProductByName(name);
    }

    @Override
    public Product findProduct(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public List<Product> findProductsByCategoryName(String categoryName) {
        List<Product> productList = new ArrayList<>();
        Category category = categoryService.findByName(categoryName);
        for (Product product:productRepository.findAll()) {
            if (product.getCategoryId().equals(category.getId())) productList.add(product);
        }
        return productList;
    }

    @Override
    public ProductReviewResponse addReview(ProductReviewRequest productReviewRequest, Long userId) {
        Product product = findProduct(productReviewRequest.getProductName());
        Review review = new Review();
        review.setComment(productReviewRequest.getMessage());
        review.setDate(LocalDateTime.now());
        review.setRating(productReviewRequest.getRating());
        review.setUserId(userId);
        review.setProductId(product.getId());
        Review savedReview = reviewService.saveReview(review);


        double total = review.getRating().getValue();
        double averageRating = product.getAverageRating();
        averageRating = (averageRating + total) / 2;
        product.setAverageRating(averageRating);

        productRepository.save(product);

        ProductReviewResponse productReviewResponse = new ProductReviewResponse();
        productReviewResponse.setMessage("Review Added Successfully");
        return productReviewResponse;
    }


}
