package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Model.SellerModel;
import com.example.Real_Estate_Platform.Service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @PostMapping("/registerSeller")
    public SellerModel registerSeller(@RequestParam("mid")int mid,@RequestBody @Valid SellerModel sellerModel){
        return sellerService.add(mid,sellerModel);
    }
    @GetMapping("/sellerLogin")
    public  String sellerLogin(@RequestParam("username")String username, @RequestParam("password")String password){
        return sellerService.sellerLogin(username,password);
    }
    @PostMapping("/addProperty/{id}")
    public PropertyModel addProperty(@PathVariable int id, @RequestBody PropertyModel propertyModel) {
        return sellerService.addProperty(propertyModel, id);
    }
    @GetMapping("/allProperties/{id}")
    public List<PropertyModel> allProperties(@PathVariable int id){
        return (List<PropertyModel>) sellerService.getAllProperties(id);
    }
}
