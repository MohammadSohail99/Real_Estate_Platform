package com.example.Real_Estate_Platform.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Property {
    @Id
    private int id;
    private String title;
    private String address;
    private double price;
    private double area;
    private boolean isSold;
    private String status;
   @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "property_buyers",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "buyer_id")
    )
    private List<Buyer> buyers = new ArrayList<>();

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }
}
