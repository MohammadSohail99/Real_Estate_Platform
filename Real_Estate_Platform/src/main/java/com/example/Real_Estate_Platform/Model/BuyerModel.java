package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"mediator"})
public class BuyerModel {
    @Valid
    private int bid;
    @NotNull(message = "Buyer Name should not be null")
    private String bname;
    @NotBlank(message = "User Name is mandatory")
    private String username;
    @Size(min = 5,message = "Password should be at least 5 characters")
    private String password;
    @NotNull(message = "Address should not be null")
    private String address;
    //@Length(message = "Mobile No should be 10 digits")
    private Long mobile;
    @NotBlank(message = "email mandatory")
    @Email(message = "email should end with @gmail.com")
    private String email;
    private Mediator mediator;
    private List<Seller> sellersToApprove = new ArrayList<>();

    @Override
    public String toString() {
        return "BuyerModel{" +
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
