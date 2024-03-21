package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentServiceImpl {

    Appointment scheduleAppointment(int mediatorId, int buyerId, int propertyId, LocalDate date);

    List<Appointment> getAllAppointments();

    void confirmAppointment(int appointmentId);

    void rejectAppointment(int appointmentId);

    List<Appointment> getAppointmentsByBuyerId(int bid);
}

