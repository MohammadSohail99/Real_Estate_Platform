package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Model.AppointmentModel;
import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Service.AppointmentService;
import com.example.Real_Estate_Platform.Service.MediatorService;
import com.example.Real_Estate_Platform.Service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/scheduleAppointment")
    public String showAppointmentForm(@RequestParam("address")String address,@RequestParam("minPrice") double minPrice,@RequestParam("minArea") double minArea,@RequestParam("maxPrice") double maxPrice,@RequestParam("maxArea") double maxArea,@RequestParam("propertyId") int id,@RequestParam("buyerId")int bid, Model model) {
        model.addAttribute("propertyId",id);
        model.addAttribute("buyerId",bid);
        model.addAttribute("address",address);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        model.addAttribute("minArea", minArea);
        model.addAttribute("maxArea", maxArea);
        return "bookAppointment";
    }
    @RequestMapping("/scheduleAppointmentBuyer")
    public String scheduleAppointment(@RequestParam("address")String address,@RequestParam("minPrice") double minPrice,@RequestParam("minArea") double minArea,@RequestParam("maxPrice") double maxPrice,@RequestParam("maxArea") double maxArea,@RequestParam int mediatorId, @RequestParam("bid") int bid, @RequestParam("id") int id, @RequestParam String dateTime, Model model) {
        LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTime);
        Appointment appointment = appointmentService.scheduleAppointment(mediatorId, bid, id, appointmentDateTime);
        model.addAttribute("appointment", appointment);
        model.addAttribute("bid",bid);
        model.addAttribute("id",id);
        model.addAttribute("address",address);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        model.addAttribute("minArea", minArea);
        model.addAttribute("maxArea", maxArea);
        return "confirmation";
    }
}
