package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Review;
import com.essies.ecommerce.data.repository.ReviewRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteAll() {
        reviewRepository.deleteAll();
    }
}
