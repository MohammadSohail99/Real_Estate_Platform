package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Model.BuyerModel;
import com.example.Real_Estate_Platform.Service.BuyerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/registerBuyer")
    public BuyerModel registerBuyer(@RequestParam("mid") int mid,@RequestBody @Valid BuyerModel buyerModel){
        return buyerService.add(mid,buyerModel);
    }
    @GetMapping("/buyerLogin")
    public  String buyerLogin(@RequestParam("username")String username, @RequestParam("password")String password){
        return buyerService.buyerLogin(username,password);
    }
    @GetMapping("/search")
    public List<Property> searchProperties(@RequestParam String address,@RequestParam double price){
        return buyerService.searchProperties(address,price);
    }
    @PostMapping("/buy")
    public String buyProperty(@RequestParam int sid, @RequestParam String address, @RequestParam double price,@RequestParam int bid ) {
        boolean success = buyerService.buyProperty(sid, address, price,bid);
        return success ? "Property bought through Seller successfully" : "Failed to buy property Already Bought Property";
    }
}
