package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({"seller"})
public class MediatorModel {
    private int mid;
    @NotNull(message = "Mediator Name should not be null")
    private String mname;
    @NotBlank(message = "User Name is mandatory")
    private String username;
    @Size(min = 5,message = "Password should be at least 5 characters")
    private String password;
    @NotBlank(message = "email mandatory")
    @Email(message = "email should end with @gmail.com")
    private String email;
    //@Length(message = "Mobile No should be 10 digits")
    private Long mobile;
    private List<Buyer> buyerList=new ArrayList<>();
    private List<Seller> sellerList=new ArrayList<>();

    @Override
    public String toString() {
        return "MediatorModel{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                '}';
    }
}
