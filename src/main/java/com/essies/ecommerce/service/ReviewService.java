package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Review;

public interface ReviewService {
    Review saveReview(Review review);

    void deleteAll();
}
