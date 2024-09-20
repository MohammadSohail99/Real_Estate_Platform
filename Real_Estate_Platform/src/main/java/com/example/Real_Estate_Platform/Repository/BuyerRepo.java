package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer,Integer> {
    Buyer findByUsername(String username);

}
