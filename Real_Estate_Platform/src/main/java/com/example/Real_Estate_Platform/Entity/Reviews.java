package com.example.Real_Estate_Platform.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @ManyToOne
        @JoinColumn(name = "property_id")
        private Property property;

        @ManyToOne
        @JoinColumn(name = "buyer_id")
        private Buyer buyer;

        private int rating;

        private String comment;
}
