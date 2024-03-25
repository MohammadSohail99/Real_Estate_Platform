package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Mediator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"mediator"})
public class BuyerModel {
    private int bid;
    private String bname;
    private String username;
    private String password;
    private String address;
    private Long mobile;
    private String email;
    private String role;
    private List<Mediator> mediatorList=new ArrayList<>();
}
