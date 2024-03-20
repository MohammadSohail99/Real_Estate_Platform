package com.example.Real_Estate_Platform.Entity;

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
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "buyer")
    private List<Property> propertyList;
}
