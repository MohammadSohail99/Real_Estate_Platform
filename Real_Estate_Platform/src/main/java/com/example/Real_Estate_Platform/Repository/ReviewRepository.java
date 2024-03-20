package com.example.Real_Estate_Platform.Repository;

import com.example.Real_Estate_Platform.Entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews ,Integer> {

}
