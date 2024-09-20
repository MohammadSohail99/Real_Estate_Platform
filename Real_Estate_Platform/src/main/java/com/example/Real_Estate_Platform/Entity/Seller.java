package com.example.Real_Estate_Platform.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    private int sid;
    private String sname;
    private String email;
    private Long mobile;
    private String username;
    private  String password;
    private String role;

    @OneToMany(mappedBy = "seller" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Property> propertyList=new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "mediator_id")
    private Mediator mediator;

    @ManyToMany(mappedBy = "seller")
    private List<Buyer> buyersToApprove = new ArrayList<>();

    @Override
    public String toString() {
        return "Seller{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                '}';
    }
}
