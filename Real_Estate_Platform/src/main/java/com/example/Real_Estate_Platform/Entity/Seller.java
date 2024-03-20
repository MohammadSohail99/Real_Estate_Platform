package com.example.Real_Estate_Platform.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String sname;
    private String username;
    private String password;
    private String email;
    private Long mobile;
    private String role;

    @OneToMany(mappedBy = "seller" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Property> propertyList=new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "mediator_id")
    private Mediator mediator;

}
