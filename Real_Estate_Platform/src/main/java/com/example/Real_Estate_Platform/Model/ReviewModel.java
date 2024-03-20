package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewModel {
    private int id;
    private Property property;
    private Buyer buyer;
    private int rating;
    private String comment;
}
