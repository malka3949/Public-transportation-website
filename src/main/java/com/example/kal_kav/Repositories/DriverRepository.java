package com.example.kal_kav.Repositories;

import com.example.kal_kav.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DriverRepository extends JpaRepository<driver, Long> {

}
