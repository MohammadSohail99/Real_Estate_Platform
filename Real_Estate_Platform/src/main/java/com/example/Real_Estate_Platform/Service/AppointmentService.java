package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Repository.AppointmentRepo;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.ServiceImplementation.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements AppointmentServiceImpl {

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private MediatorRepo mediatorRepo;

    @Override
    public Appointment scheduleAppointment(String mediator_name, String buyer_name, String title, LocalDate date) {
        if (appointmentRepo.existsByDate(date)) {
            throw new IllegalArgumentException("Appointment slot is already booked for this property on the selected date");
        }
        Appointment appointment = new Appointment();
        appointment.setMediator_name(mediator_name);
        appointment.setBuyer_name(buyer_name);
        appointment.setTitle(title);
        appointment.setDate(date);
        appointment.setStatus("pending");
        appointmentRepo.save(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments(int mid) {
        Mediator mediator=mediatorRepo.getReferenceById(mid);
        return appointmentRepo.findAll().stream().filter(appointment -> appointment.getMediator_name().equalsIgnoreCase(mediator.getMname())).collect(Collectors.toList());
    }
    @Override
    public void confirmAppointment(int appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        appointment.setStatus("Confirmed");
        appointmentRepo.save(appointment);
    }
    @Override
    public void rejectAppointment(int appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        appointment.setStatus("Rejected");
        appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByBuyerName(String buyer_name) {
        return appointmentRepo.findAll()
                .stream()
                .filter(appointment -> appointment.getBuyer_name().equalsIgnoreCase(buyer_name) )
                .collect(Collectors.toList());
    }
}
