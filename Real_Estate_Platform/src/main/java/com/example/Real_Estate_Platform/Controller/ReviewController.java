package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Model.ReviewModel;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Service.BuyerService;
import com.example.Real_Estate_Platform.Service.PropertyService;
import com.example.Real_Estate_Platform.Service.ReviewService;
import com.example.Real_Estate_Platform.ServiceImplementation.BuyerServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.PropertyServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private BuyerServiceImpl buyerService;


    @RequestMapping("/addReview")
    public String addReview(@RequestParam("pid") int propertyId,
                            @RequestParam("buyer_name") String buyer_name, Model model){
        Property property =propertyService.getPropertyById(propertyId);
        Buyer buyer = buyerService.getBuyerByName(buyer_name);
        ReviewModel reviewModel=new ReviewModel();
        model.addAttribute("reviewModel",reviewModel);
        model.addAttribute("pid",propertyId);
        model.addAttribute("buyer_name",buyer_name);
        return "addReviewForm";
    }
    @RequestMapping("/submitReview")
    public String submitReview(@RequestParam("pid") int propertyId,
                               @RequestParam("buyer_name") String buyer_name,
                               @RequestParam("reviewText") String reviewText,
                               @RequestParam("rating") int rating) {
        Property property =propertyService.getPropertyById(propertyId);
        Buyer buyer = buyerService.getBuyerByName(buyer_name);
        System.out.println(buyer);
        reviewService.addReview(property, buyer, reviewText, rating);
        System.out.println(buyer);
        return "thankYouForReview";
    }

}
