package com.example.Real_Estate_Platform.Model;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {
    private int id;
    @NotNull(message = "Title should not be null")
    private String title;
    @NotNull(message = "Address should not be null")
    private String address;
    @NotNull(message = "price should not be null")
    private double price;
    @NotNull(message = "area should not be null")
    private double area;
    private boolean isSold;
    private String status;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Seller seller;
    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean isSold) {
        this.isSold = isSold;
    }

    @Override
    public String toString() {
        return "PropertyModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", isSold=" + isSold +
                '}';
    }
}
