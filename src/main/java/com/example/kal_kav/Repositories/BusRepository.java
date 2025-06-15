package com.example.kal_kav.Repositories;

import com.example.kal_kav.Models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

}
