package com.example.Real_Estate_Platform.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String address;
    private double price;
    private double area;
    private boolean isSold;
    private String status="Pending";

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

}
