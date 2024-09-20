package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Model.BuyerModel;
import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Model.SellerModel;
import com.example.Real_Estate_Platform.Service.BuyerService;
import com.example.Real_Estate_Platform.Service.MediatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MediatorController {
    @Autowired
    private MediatorService mediatorService;

    @PostMapping("/registerMediator")
    public MediatorModel registerMediator(@RequestBody @Valid MediatorModel mediatorModel){
        return mediatorService.add(mediatorModel);
    }
    @GetMapping("/mediatorLogin")
    public  String mediatorLogin(@RequestParam("username")String username, @RequestParam("password")String password){
        return mediatorService.mediatorLogin(username,password);
    }
    @GetMapping("/allSellers/{id}")
    public List<SellerModel> allSellers() {
        return (List<SellerModel>) mediatorService.getAllSellers();
    }

    @GetMapping("allBuyers/{id}")
    public List<BuyerModel> allBuyers(){
        return mediatorService.getAllBuyers();
    }

    @GetMapping("/propertyInfo/{propertyId}")
    public PropertyModel getPropertyInfo(@PathVariable int propertyId) {
        return mediatorService.getPropertyInfo(propertyId);
    }

    @GetMapping("/buyerInfo/{propertyId}")
    public List<BuyerModel> getBuyersForProperty(@PathVariable int propertyId) {
        return mediatorService.getBuyersForProperty(propertyId);
    }
}
