package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({"seller"})
public class MediatorModel {
    private int mid;
    private String mname;
    private String username;
    private String password;
    private String email;
    private Long mobile;
    private String role;
    private List<Buyer> buyerList=new ArrayList<>();
    private List<Seller> sellerList=new ArrayList<>();
}
