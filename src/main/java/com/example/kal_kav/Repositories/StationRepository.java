package com.example.kal_kav.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kal_kav.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
Station  findByNumber(int number);


}
