package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

    boolean existsByDate(LocalDate date);
}
