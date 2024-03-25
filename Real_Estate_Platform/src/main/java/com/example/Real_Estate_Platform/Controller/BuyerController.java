package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Model.BuyerModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.ServiceImplementation.AppointmentServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.BuyerServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.PropertyServiceImpl;
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
    private BuyerServiceImpl buyerService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private AppointmentServiceImpl appointmentService;
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
                return "redirect:/all?buyer_name=" + buyerModel.getBname();
            } else {
                model.addAttribute("loginFailed", true);
                return "notaccept";
    }
}
    @RequestMapping("/all")
        public String all(@RequestParam("buyer_name") String buyer_name, Model model){
        List<PropertyModel> propertyModelList=propertyService.getAllProperty();
        model.addAttribute("buyer_name",buyer_name);
        model.addAttribute("property",propertyModelList);
        return "viewPropertiesforBuyer";
    }
    @RequestMapping("/searching")
    public String searching(@RequestParam("buyer_name") String buyer_name,@ModelAttribute("property") PropertyModel property, Model model) {
        model.addAttribute("buyer_name", buyer_name);
        model.addAttribute("property",property);
        return "search";
    }
    @RequestMapping("/search")
    public String searchProperties(@ModelAttribute("property") PropertyModel property,@RequestParam("buyer_name") String buyer_name, @RequestParam("address") String address, @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice,@RequestParam("maxArea")double maxArea,@RequestParam("minArea") double minArea, Model model) {
        List<Property> searchResults = propertyService.searchProperties(address, minPrice, maxPrice,minArea,maxArea);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("title",property.getTitle());
        model.addAttribute("pid",property.getId());
        model.addAttribute("buyer_name", buyer_name);
        return "viewProperties";
    }
    @RequestMapping("/searchBasedLocation")
    public String searchLocation(@RequestParam("buyer_name") String buyer_name, Model model) {
        model.addAttribute("buyer_name", buyer_name);
        return "searchLocation";
    }
    @RequestMapping("/searchLocation")
    public String searchBasedOnLocation(@RequestParam("buyer_name") String buyer_name, @RequestParam("address") String address,Model model) {
        List<Property> searchResults = propertyService.searchPropertyByLocation(address);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("buyer_name", buyer_name);
        return "viewPropertiesByLocation";
    }
    @RequestMapping("/searchBasedArea")
    public String searchArea(@RequestParam("buyer_name") String  buyer_name, Model model) {
        model.addAttribute("buyer_name", buyer_name);
        return "searchArea";
    }
    @RequestMapping("/searchArea")
    public String searchBasedOnArea(@RequestParam("buyer_name") String buyer_name,@RequestParam("minArea")double minArea,@RequestParam("maxArea")double maxArea, Model model) {
        List<Property> searchResults = propertyService.searchPropertyByArea(minArea,maxArea);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("buyer_name", buyer_name);
        model.addAttribute("minArea", minArea);
        model.addAttribute("maxArea", maxArea);
        return "viewPropertiesByArea";
    }
    @RequestMapping("/searchBasedPrice")
    public String searchPrice(@RequestParam("buyer_name") String buyer_name, Model model) {
        model.addAttribute("buyer_name", buyer_name);
        return "searchPrice";
    }
    @RequestMapping("/searchPrice")
    public String searchBasedOnPrice(@RequestParam("buyer_name") String buyer_name,@RequestParam("minPrice")double minPrice ,@RequestParam("maxPrice")double maxPrice, Model model) {
        List<Property> searchResults = propertyService.searchPropertyByPrice(minPrice,maxPrice);
        model.addAttribute("propertyList", searchResults);
        model.addAttribute("buyer_name", buyer_name);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        return "viewPropertiesByPrice";
    }
    @GetMapping("/viewBookings")
    public String viewBuyerAppointments(@RequestParam("pid")int id,@RequestParam("mediator_name") String mediator_name,@RequestParam("title")String title,@RequestParam("buyer_name") String buyer_name, Model model) {
        List<Appointment> appointments = appointmentService.getAppointmentsByBuyerName(buyer_name);
        model.addAttribute("appointments", appointments);
        model.addAttribute("title",title);
        model.addAttribute("pid",id);
        model.addAttribute("buyer_name", buyer_name);
        model.addAttribute("mediator_name",mediator_name);
        return "buyerBookings";
    }
    @RequestMapping("/buy")
    public String buyProperty(@RequestParam("buyer_name") String buyer_name, @RequestParam("title") String title,@RequestParam("mediator_name") String mediator_name,@RequestParam("pid")int id, Model model) {
        Property property = propertyService.getPropertyById(id);
        Buyer buyer = buyerService.getBuyerByName(buyer_name);
        if (property == null) {
            return "redirect:/error";
        } else if (property.isSold()) {
            model.addAttribute("buyer_name",buyer_name);
            model.addAttribute("title",title);
            model.addAttribute("mediator_name",mediator_name);
            model.addAttribute("pid",id);
            return "Already_Sold";
        } else {
            double totalPrice = propertyService.calculateTotalPriceForArea(id,buyer_name);
            model.addAttribute("pid", id);
            model.addAttribute("buyer_name", buyer_name);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("property",property);
            model.addAttribute("buyer",buyer);
            return "property_details";
        }
    }
}
