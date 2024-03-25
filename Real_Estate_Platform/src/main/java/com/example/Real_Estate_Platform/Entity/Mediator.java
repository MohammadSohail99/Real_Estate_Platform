package com.example.Real_Estate_Platform.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor
@NoArgsConstructor
public class Mediator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mid;
    private String mname;
    private String username;
    private String password;
    private String email;
    private Long mobile;
    private String role;

    @OneToMany(mappedBy = "mediator",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Seller> sellerList=new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "mediator_buyer",
            joinColumns = { @JoinColumn(name = "mediator_id") },
            inverseJoinColumns = { @JoinColumn(name = "buyer_id") }
    )
    private List<Buyer> buyerList = new ArrayList<>();


}
