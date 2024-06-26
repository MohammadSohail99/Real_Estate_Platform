package com.example.Real_Estate_Platform.Model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModel {
    private int id;
    private String Mediator_name;

    private String Buyer_name;

    private String title;

    private LocalDate date;

    private String status="Pending";
}
