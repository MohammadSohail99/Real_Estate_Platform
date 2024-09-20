package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Integer> {
    Seller findByUsername(String username);

    boolean existsByUsername(String username);
}
