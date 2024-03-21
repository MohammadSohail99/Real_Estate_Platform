package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Service.AppointmentService;
import com.example.Real_Estate_Platform.ServiceImplementation.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;

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
    public String scheduleAppointment(@RequestParam("address") String address, @RequestParam("minPrice") double minPrice, @RequestParam("minArea") double minArea, @RequestParam("maxPrice") double maxPrice, @RequestParam("maxArea") double maxArea, @RequestParam("mediatorId") int mediatorId, @RequestParam("buyerId") int bid, @RequestParam("propertyId") int id, @RequestParam("date") LocalDate date, Model model) {
        try {
            Appointment appointment = appointmentService.scheduleAppointment(mediatorId, bid, id,date);
            model.addAttribute("appointment", appointment);
            model.addAttribute("buyerId", bid);
            model.addAttribute("propertyId", id);
            model.addAttribute("address", address);
            model.addAttribute("minPrice", minPrice);
            model.addAttribute("maxPrice", maxPrice);
            model.addAttribute("minArea", minArea);
            model.addAttribute("maxArea", maxArea);
            return "confirmation";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Appointment slot is already booked for this property on the selected date");
            model.addAttribute("mediatorId", mediatorId);
            model.addAttribute("buyerId", bid);
            model.addAttribute("propertyId", id);
            model.addAttribute("address", address);
            model.addAttribute("minPrice", minPrice);
            model.addAttribute("maxPrice", maxPrice);
            model.addAttribute("minArea", minArea);
            model.addAttribute("maxArea", maxArea);
            return "bookAppointment";
        }
    }
}
