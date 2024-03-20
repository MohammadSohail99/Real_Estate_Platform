package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Model.ReviewModel;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Service.BuyerService;
import com.example.Real_Estate_Platform.Service.PropertyService;
import com.example.Real_Estate_Platform.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private BuyerService buyerService;


    @RequestMapping("/addReview")
    public String addReview(@RequestParam("propertyId") int propertyId,
                            @RequestParam("buyerId") int buyerId, Model model){
        Property property =propertyService.getPropertyById(propertyId);
        Buyer buyer = buyerService.getBuyerById(buyerId);
        ReviewModel reviewModel=new ReviewModel();
        model.addAttribute("reviewModel",reviewModel);
        model.addAttribute("propertyId",propertyId);
        model.addAttribute("buyerId",buyerId);
        return "addReviewForm";
    }
    @RequestMapping("/submitReview")
    public String submitReview(@RequestParam("propertyId") int propertyId,
                               @RequestParam("buyerId") int buyerId,
                               @RequestParam("reviewText") String reviewText,
                               @RequestParam("rating") int rating) {
        Property property =propertyService.getPropertyById(propertyId);
        Buyer buyer = buyerService.getBuyerById(buyerId);
        reviewService.addReview(property, buyer, reviewText, rating);
        return "thankYouForReview";
    }

}
