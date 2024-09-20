package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Mediator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.Media;
@Repository
public interface MediatorRepo extends JpaRepository<Mediator,Integer> {
    Mediator findByUsername(String username);

    boolean existsByUsername(String username);
}
