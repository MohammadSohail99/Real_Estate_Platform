package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Service.PropertyService;
import com.example.Real_Estate_Platform.Service.SellerService;
import com.example.Real_Estate_Platform.ServiceImplementation.PropertyServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.SellerServiceImpl;
import com.example.Real_Estate_Platform.validation.ValidationProperty;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PropertyController {
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private SellerServiceImpl sellerService;
    @Autowired
    private ValidationProperty validationProperty;
    @RequestMapping("/main")
    public String homepage(@RequestParam("sid") int sid, Model model) {
        Seller seller = sellerService.getSellerById(sid);
        if (seller != null) {
            List<Property> properties = sellerService.getProperties(sid);
            List<Buyer> buyers=new ArrayList<>();
            properties.forEach(i->buyers.add(i.getBuyer()));
            model.addAttribute("buyers",buyers);
            model.addAttribute("seller", seller);
            model.addAttribute("properties", properties);
        }
        return "home";
    }
    @RequestMapping("/addProperty")
    public String addProperty(@RequestParam("sid") int sid, ModelMap model){
        PropertyModel propertyModel=new PropertyModel();
        model.addAttribute("propertyModel",propertyModel);
        model.addAttribute("sid",sid);
        return "addPropertyForm";
    }
    @RequestMapping("/saveProperty")
    public String saveProperty(@Valid @RequestParam("sid") int sid, @ModelAttribute("propertyModel") PropertyModel propertyModel, BindingResult bindingResult, Model model){
        validationProperty.validate(propertyModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "addPropertyForm";
        }
        model.addAttribute(propertyService.saveProperty(propertyModel,sid));
        List<PropertyModel> propertyList=propertyService.getAllProperties(sid);
        model.addAttribute("propertyList",propertyList);
        model.addAttribute("sid",sid);
        return "viewAllProperties";
    }
    @RequestMapping("/viewProperties")
    public String viewProperties(Model model){
        model.addAttribute("propertyList",propertyService.getAllProperty());
        return "viewAllProperties";
    }
    @RequestMapping("/viewProperty")
    public String viewProperty(@RequestParam("id") int id, Model model) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "viewProperty";
        }
        return "error";
    }
    @RequestMapping("/updatePropertyForm")
    public String updatePropertyForm(@RequestParam("id") int id, Model model) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "updatePropertyForm";
        } else {
            return "error";
        }
    }

    @RequestMapping("/updateProperty")
    public String updatePropertySubmit(@ModelAttribute Property property) {
        propertyService.updateProperty(property);
        return "redirect:/viewProperty?id=" + property.getId();
    }
    @RequestMapping("/confirmDelete")
    public String confirmDelete(@RequestParam("id") int id, Model model) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "confirmDelete";
        } else {
            return "error";
        }
    }
    @RequestMapping("/deleteProperty")
    public String deleteProperty(@RequestParam("id") int id) {
        propertyService.deletePropertyById(id);
        return "redirect:/viewProperties";
    }
}
