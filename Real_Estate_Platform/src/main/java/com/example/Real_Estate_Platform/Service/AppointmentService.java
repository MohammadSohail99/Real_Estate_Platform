package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Entity.Appointment;
import com.example.Real_Estate_Platform.Repository.AppointmentRepo;
import com.example.Real_Estate_Platform.ServiceImplementation.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService implements AppointmentServiceImpl {
    @Autowired
    private AppointmentRepo appointmentRepo;

    public Appointment scheduleAppointment(int mediatorId, int buyerId, int propertyId, LocalDateTime dateTime) {
        Appointment appointment = new Appointment();
        appointment.setMediatorId(mediatorId);
        appointment.setBuyerId(buyerId);
        appointment.setPropertyId(propertyId);
        appointment.setDateTime(dateTime);
        appointment.setStatus("Confirm");
        appointmentRepo.save(appointment);
        return appointment;
    }
}
