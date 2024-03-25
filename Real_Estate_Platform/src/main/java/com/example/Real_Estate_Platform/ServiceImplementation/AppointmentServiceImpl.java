package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentServiceImpl {
    Appointment scheduleAppointment(String mediator_name, String buyer_name, String title, LocalDate date);

    List<Appointment> getAllAppointments(int mid);

    void confirmAppointment(int appointmentId);

    void rejectAppointment(int appointmentId);

    List<Appointment> getAppointmentsByBuyerName(String buyer_name);
}

