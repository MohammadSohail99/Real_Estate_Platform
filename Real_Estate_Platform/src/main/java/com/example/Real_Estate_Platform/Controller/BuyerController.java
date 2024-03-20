package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Model.BuyerModel;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Service.BuyerService;
import com.example.Real_Estate_Platform.Service.PropertyService;
import com.example.Real_Estate_Platform.validation.ValidationBuyer;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class BuyerController {
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private ValidationBuyer validationBuyer;

    @RequestMapping("/buyer")
    public  String buyerPage(){
        return "first";
    }
    @RequestMapping("/regBuyer")
    public String showRegisterForm(Model model) {
        BuyerModel buyerModel = new BuyerModel();
        model.addAttribute("buyer", buyerModel);
        return "buyerRegisterForm";
    }

    @RequestMapping("/registerBuyers")
    public String registerBuyer(@Valid @ModelAttribute("buyer") BuyerModel buyerModel, BindingResult bindingResult) {
        validationBuyer.validate(buyerModel,bindingResult);
        if(bindingResult.hasErrors()) return "buyerRegisterForm";
        buyerService.registerBuyer(buyerModel);
        return "redirect:/loginB";
    }

    @RequestMapping("/loginB")
    public String showLoginForm(Model model) {
        BuyerModel buyerModel = new BuyerModel();
        model.addAttribute("buyer", buyerModel);
        return "buyerLoginForm";
    }
@RequestMapping("/loginBuyer")
public String loginBuyer(@RequestParam String username, @RequestParam String password, Model model) {
    BuyerModel buyerModel = buyerService.loginBuyer(username, password);
    if (buyerModel != null) {
        model.addAttribute("buyer", buyerModel);
        return "redirect:/all?bid=" + buyerModel.getBid();
    } else {
        model.addAttribute("loginFailed", true);
        return "notaccept";
    }
}
@RequestMapping("/all")
        public String all(@RequestParam("bid") int bid, Model model){
        model.addAttribute("bid",bid);
        return "viewPropertiesforBuyer";
}
    @RequestMapping("/searching")
    public String searching(@RequestParam("bid") int bid, Model model) {
        model.addAttribute("bid", bid);
        return "search";
    }
    @RequestMapping("/search")
    public String searchProperties(@RequestParam("bid") int bid, @RequestParam("address") String address, @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice,@RequestParam("maxArea")double maxArea,@RequestParam("minArea") double minArea, Model model) {
        List<Property> searchResults = propertyService.searchProperties(address, minPrice, maxPrice,minArea,maxArea);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("bid", bid);
        model.addAttribute("address",address);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        model.addAttribute("minArea", minArea);
        model.addAttribute("maxArea", maxArea);
        return "viewProperties";
    }
    @RequestMapping("/search1")
    public String search1(@RequestParam("bid") int bid, Model model) {
        model.addAttribute("bid", bid);
        return "searchLocation";
    }
    @RequestMapping("/searchLocation")
    public String searchProp(@RequestParam("bid") int bid, @RequestParam("address") String address,Model model) {
        List<Property> searchResults = propertyService.searchPropertyByLocation(address);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("bid", bid);
        return "viewPropertiesByLocation";
    }
    @RequestMapping("/search2")
    public String search2(@RequestParam("bid") int bid, Model model) {
        model.addAttribute("bid", bid);
        return "searchArea";
    }
    @RequestMapping("/searchArea")
    public String searchProparea(@RequestParam("bid") int bid,@RequestParam("minArea")double minArea,@RequestParam("maxArea")double maxArea, HttpSession session, Model model) {
        List<Property> searchResults = propertyService.searchPropertyByArea(minArea,maxArea);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("bid", bid);
        model.addAttribute("minArea", minArea);
        model.addAttribute("maxArea", maxArea);
        return "viewPropertiesByArea";
    }
    @RequestMapping("/search3")
    public String search3(@RequestParam("bid") int bid, Model model) {
        model.addAttribute("bid", bid);
        return "searchPrice";
    }
    @RequestMapping("/searchPrice")
    public String searchProprice(@RequestParam("bid") int bid,@RequestParam("minPrice")double minPrice ,@RequestParam("maxPrice")double maxPrice, HttpSession session, Model model) {
        List<Property> searchResults = propertyService.searchPropertyByPrice(minPrice,maxPrice);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("bid", bid);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        return "viewPropertiesByPrice";
    }
    @RequestMapping("/buy")
    public String buyProperty(@RequestParam("bid") int bid, @RequestParam("id") int id,@RequestParam("address")String address,@RequestParam("minPrice")double minPrice,@RequestParam("maxPrice") double maxPrice,@RequestParam("maxArea")double maxArea,@RequestParam("minArea") double minArea, Model model) {
        Property property = propertyService.getPropertyById(id);
        Buyer buyer = buyerService.getBuyerById(bid);
        if (property == null) {
            return "redirect:/error";
        } else if (property.isSold()) {
            model.addAttribute("bid",bid);
            model.addAttribute("id",id);
            model.addAttribute("address",address);
            model.addAttribute("minPrice",minPrice);
            model.addAttribute("maxPrice",maxPrice);
            return "Already_Sold";
        } else {
            double totalPrice = propertyService.calculateTotalPriceForArea(property.getArea(), property.getPrice(), property.getId(), bid);
            model.addAttribute("property", property);
            model.addAttribute("buyer", buyer);
            model.addAttribute("totalPrice", totalPrice);
            return "property_details";
        }
    }
}
