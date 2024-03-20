package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Integer> {
    Optional<Seller> findByUsername(String username);
    boolean existsByUsername(String username);
}
