package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Reviews;
import com.example.Real_Estate_Platform.Repository.ReviewRepository;
import com.example.Real_Estate_Platform.ServiceImplementation.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements ReviewServiceImpl {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public void addReview(Property property, Buyer buyer, String reviewText, int rating) {
        if (property != null && buyer != null) {
            Reviews review = new Reviews();
            review.setProperty(property);
            review.setBuyer(buyer);
            review.setComment(reviewText);
            review.setRating(rating);
            reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Property or buyer not found");
        }
    }
}
