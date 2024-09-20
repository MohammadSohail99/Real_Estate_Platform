package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property,Integer> {
    List<Property> findByAddressAndPrice(String address, double price);
    @Query("SELECT p FROM Property p WHERE p.seller = :seller AND p.address = :address AND p.price = :price")
    Property findBySellerAndAddressAndPrice(@Param("seller") Seller seller, @Param("address") String address, @Param("price") double price);
}
