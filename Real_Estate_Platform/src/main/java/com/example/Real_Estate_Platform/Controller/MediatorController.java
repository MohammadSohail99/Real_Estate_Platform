package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.AppointmentModel;
import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Service.BuyerService;
import com.example.Real_Estate_Platform.Service.MediatorService;
import com.example.Real_Estate_Platform.Service.PropertyService;
import com.example.Real_Estate_Platform.Service.SellerService;
import com.example.Real_Estate_Platform.ServiceImplementation.*;
import com.example.Real_Estate_Platform.validation.ValidationMediator;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MediatorController {
    @Autowired
    private MediatorServiceImpl mediatorService;
    @Autowired
    private SellerServiceImpl sellerService;
    @Autowired
    private BuyerServiceImpl buyerService;
    @Autowired
    private PropertyServiceImpl propertyService;
    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    ValidationMediator validationMediator;
    @RequestMapping("/")
    public String start(){
        return "start";
    }
        @RequestMapping("/registerMediator")
        public String showRegisterForm(Model model) {
            MediatorModel mediatorModel=new MediatorModel();
            model.addAttribute("mediator",mediatorModel);
            return "registerForm";
        }
        @RequestMapping("/register")
        public String registerMediator(@Valid  @ModelAttribute("mediator") MediatorModel mediatorModel, BindingResult bindingResult) {
            validationMediator.validate(mediatorModel,bindingResult);
            if(bindingResult.hasErrors()){
                return "registerForm";
            }
            mediatorService.registerMediator(mediatorModel);
            return "redirect:/loginM";
        }
        @RequestMapping("/loginM")
        public String showLoginForm(Model model) {
            MediatorModel mediatorModel=new MediatorModel();
            model.addAttribute("mediator",mediatorModel);
            model.addAttribute("id",mediatorModel.getMid());
            return "loginForm";
        }

        @RequestMapping("/loginMediator")
        public String loginMediator(@RequestParam String username, @RequestParam String password,Model model){
            MediatorModel mediatorModel = mediatorService.loginMediator(username, password);
            if (mediatorModel != null) {
                model.addAttribute("mediator", mediatorModel);
                model.addAttribute("username",mediatorModel.getUsername());
                return "redirect:/viewAllSeller?mid="+mediatorModel.getMid();
            } else {
                model.addAttribute("loginFailed", true);
                return "notaccept";
            }
        }
    @RequestMapping("/viewAllSeller")
    public String getAllSellers(@RequestParam("mid") int mid, Model model) {
        List<Seller> sellers= sellerService.getAllSellers(mid);
        model.addAttribute("sellers", sellers);
        model.addAttribute("mid",mid);
        return "viewAllSellers";
    }
    @RequestMapping("/viewBuyer")
    public String viewBuyerInfo(@RequestParam("mid")int mid,@RequestParam("propertyId") int propertyId, Model model) {
        Property property = propertyService.getPropertyById(propertyId);
        Buyer buyer = property.getBuyer();
        model.addAttribute("buyer", buyer);
        model.addAttribute("property",property);
        model.addAttribute("mid",mid);
        double total=propertyService.calculate(property.getArea(),property.getPrice(),propertyId);
        model.addAttribute("total",total);
        return "viewAllBuyers";
    }
    @GetMapping("/viewPropertiesBySeller")
    public String viewPropertiesBySeller(@RequestParam("sellerId") int sellerId,@RequestParam("mid") int mid, Model model) {
        List<Property> properties = propertyService.getPropertiesBySellerId(sellerId);
        model.addAttribute("properties", properties);
        model.addAttribute("mid",mid);
        return "viewPropertySeller";
    }
    @RequestMapping("viewAppointments")
    public String viewAllAppointments(@RequestParam("mid")int mid, Model model){
        List<Appointment> appointmentList=appointmentService.getAllAppointments(mid);
        model.addAttribute("appointments",appointmentList);
        model.addAttribute("mid",mid);
        System.out.println(appointmentList);
        return "viewAllAppointments";
    }
    @RequestMapping("/confirmAppointment")
    public String confirmAppointment(@RequestParam("mid")int mid,@RequestParam("appointmentId") int appointmentId,Model model) {
        appointmentService.confirmAppointment(appointmentId);
        model.addAttribute("mid",mid);
        return "redirect:/viewAppointments?mid="+mid;
    }

    @RequestMapping("/rejectAppointment")
    public String rejectAppointment(@RequestParam("mid")int mid,@RequestParam("appointmentId") int appointmentId, Model model) {
        appointmentService.rejectAppointment(appointmentId);
        model.addAttribute("mid",mid);
        return "redirect:/viewAppointments?mid="+mid;
    }

}








