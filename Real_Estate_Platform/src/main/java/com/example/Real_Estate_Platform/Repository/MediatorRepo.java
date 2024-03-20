package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Mediator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediatorRepo extends JpaRepository<Mediator,Integer> {
    boolean existsByUsername(String  userName);
    Optional<Mediator> findByUsername(String username);
}
