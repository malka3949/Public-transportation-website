package com.example.kal_kav.Repositories;

import com.example.kal_kav.Models.Line;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

      Line  findByNumber( Long number);

    
}
