package com.example.Real_Estate_Platform.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Buyer {
    @Id
    private int bid;
    private String bname;
    private String username;
    private String password;
    private String address;
    private Long mobile;
    private String email;
    private String role;

    @ManyToOne
    @JoinColumn(name = "mediator_id")
    private Mediator mediator;

    @ManyToMany(mappedBy = "buyer")
    private List<Property> propertyList=new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "seller_buyer",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_id")
    )
    private List<Seller> sellersToApprove = new ArrayList<>();

    @Override
    public String toString() {
        return "Buyer{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                '}';
    }
}
