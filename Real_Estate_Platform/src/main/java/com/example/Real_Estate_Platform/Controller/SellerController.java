package com.example.Real_Estate_Platform.Controller;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.SellerModel;
import com.example.Real_Estate_Platform.Service.BuyerService;
import com.example.Real_Estate_Platform.Service.MediatorService;
import com.example.Real_Estate_Platform.Service.PropertyService;
import com.example.Real_Estate_Platform.Service.SellerService;
import com.example.Real_Estate_Platform.ServiceImplementation.BuyerServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.MediatorServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.PropertyServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.SellerServiceImpl;
import com.example.Real_Estate_Platform.validation.ValidationSeller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class SellerController {
    @Autowired
    private SellerServiceImpl sellerService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private BuyerServiceImpl buyerService;
    @Autowired
    private MediatorServiceImpl mediatorService;
    @Autowired
    ValidationSeller validationSeller;
    @RequestMapping("/regSeller")
    public String showRegisterForm(Model model) {
        SellerModel sellerModel = new SellerModel();
        model.addAttribute("seller", sellerModel);
        return "sellerRegisterForm";
    }
    @RequestMapping("/registerSellers")
    public String registerSeller(@Valid @ModelAttribute("seller") SellerModel sellerModel, BindingResult bindingResult) {
        validationSeller.validate(sellerModel,bindingResult);
        if(bindingResult.hasErrors()) return "sellerRegisterForm";
        sellerService.registerSeller(sellerModel);
        return "redirect:/logins";
    }

    @RequestMapping("/logins")
    public String showLoginForm(Model model) {
        SellerModel sellerModel = new SellerModel();
        model.addAttribute("seller", sellerModel);
        return "sellerLoginForm";
    }

    @RequestMapping("/loginSeller")
    public String loginSeller(@RequestParam String username, @RequestParam String password, Model model) {
        SellerModel sellerModel = sellerService.loginSeller(username, password);
        if (sellerModel != null) {
            model.addAttribute("seller", sellerModel);
            model.addAttribute("sid", sellerModel.getSid());
            return "redirect:/main?sid=" + sellerModel.getSid();
        } else {
            model.addAttribute("loginFailed", true);
            return "notaccept";
        }
    }

}
