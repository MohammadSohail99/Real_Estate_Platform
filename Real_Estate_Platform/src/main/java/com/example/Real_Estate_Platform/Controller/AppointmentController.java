package com.example.Real_Estate_Platform.Controller;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Repository.BuyerRepo;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Service.AppointmentService;
import com.example.Real_Estate_Platform.Service.BuyerService;
import com.example.Real_Estate_Platform.ServiceImplementation.AppointmentServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.BuyerServiceImpl;
import com.example.Real_Estate_Platform.ServiceImplementation.MediatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private BuyerServiceImpl buyerService;
    @Autowired
    private MediatorServiceImpl mediatorService;

    @Autowired
    private BuyerRepo buyerRepo;
    @Autowired
    private MediatorRepo mediatorRepo;
    @RequestMapping("/scheduleAppointment")
    public String showAppointmentForm(@RequestParam("pid")int id,@RequestParam("buyer_name")String buyer_name,@RequestParam("mediator_name")String mediator_name,@RequestParam("title") String title, Model model) {
        Buyer buyer= buyerService.getBuyerByName(buyer_name);
        List<Mediator> mediator=mediatorService.getMediator(mediator_name);
        buyer.setMediatorList(mediator);
        buyerRepo.save(buyer);
        Mediator mediator1=mediatorService.getMediatorByName(mediator_name);
        List<Buyer> buyerList=buyerService.getBuyer(buyer_name);
        mediator1.setBuyerList(buyerList);
        mediatorRepo.save(mediator1);
        model.addAttribute("pid",id);
        model.addAttribute("title",title);
        model.addAttribute("buyer_name",buyer_name);
        model.addAttribute("mediator_name",mediator_name);
        return "bookAppointment";
    }
    @RequestMapping("/scheduleAppointmentBuyer")
    public String scheduleAppointment(@RequestParam("pid")int id,@RequestParam("mediator_name") String mediator_name, @RequestParam("buyer_name") String buyer_name, @RequestParam("title") String title, @RequestParam("date") LocalDate date, Model model) {
        try {
            Appointment appointment = appointmentService.scheduleAppointment(mediator_name, buyer_name, title,date);
            model.addAttribute("appointment", appointment);
            model.addAttribute("buyer_name", buyer_name);
            model.addAttribute("title", title);
            model.addAttribute("pid",id);
            return "confirmation";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Appointment slot is already booked for this property on the selected date");
            model.addAttribute("mediator_name", mediator_name);
            model.addAttribute("buyer_name", buyer_name);
            model.addAttribute("title", title);
            model.addAttribute("pid",id);
            return "bookAppointment";
        }
    }
}
