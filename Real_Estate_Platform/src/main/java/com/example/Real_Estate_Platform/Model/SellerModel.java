package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Property;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"mediator"})
public class SellerModel {
    private int sid;
    private String sname;
    private String username;
    private String password;
    private String email;
    private Long mobile;
    private Mediator mediator;
    private String role;
    List<Property> propertyList=new ArrayList<>();
}
