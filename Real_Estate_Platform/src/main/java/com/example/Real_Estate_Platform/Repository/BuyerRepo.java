package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer,Integer> {
    Optional<Buyer> findByUsername(String username);
}
