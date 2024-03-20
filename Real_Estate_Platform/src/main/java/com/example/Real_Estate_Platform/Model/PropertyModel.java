package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {
    private int id;
    private String title;
    private String address;
    private double price;
    private double area;
    private boolean isSold;
    private String status="Pending";

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Seller seller;

    public boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(boolean sold) {
        isSold = sold;
    }
}
