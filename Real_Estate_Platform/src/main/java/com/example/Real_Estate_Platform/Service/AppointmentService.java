package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Repository.AppointmentRepo;
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

    @Override
    public Appointment scheduleAppointment(int mediatorId, int buyerId, int propertyId, LocalDate date) {
        if (appointmentRepo.existsByDate(date)) {
            throw new IllegalArgumentException("Appointment slot is already booked for this property on the selected date");
        }
        Appointment appointment = new Appointment();
        appointment.setMediatorId(mediatorId);
        appointment.setBuyerId(buyerId);
        appointment.setPropertyId(propertyId);
        appointment.setDate(date);
        appointment.setStatus("pending");
        appointmentRepo.save(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
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
    public List<Appointment> getAppointmentsByBuyerId(int bid) {
        return appointmentRepo.findAll()
                .stream()
                .filter(appointment -> appointment.getBuyerId() == bid)
                .collect(Collectors.toList());
    }
}
