package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Property;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"mediator"})
public class SellerModel {
    private int sid;
    @NotNull(message = "Seller Name should not be null")
    private String sname;
    @NotBlank(message = "email mandatory")
    @Email(message = "email should end with @gmail.com")
    private String email;
    //@Length(message = "Mobile No should be 10 digits")
    private Long mobile;
    @NotBlank(message = "User Name is mandatory")
    private String username;
    @Size(min = 5,message = "Password should be at least 5 characters")
    private  String password;
    private Mediator mediator;
    List<Property> propertyList=new ArrayList<>();
    private List<Buyer> buyersToApprove = new ArrayList<>();

    @Override
    public String toString() {
        return "SellerModel{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
