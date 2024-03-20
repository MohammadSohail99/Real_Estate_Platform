package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;

public interface ReviewServiceImpl {
    void addReview(Property property, Buyer buyer, String reviewText, int rating);
}
