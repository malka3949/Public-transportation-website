package com.example.kal_kav.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kal_kav.Models.Station_Line;
import java.util.List;

@Repository
public interface Station_LineRepository extends JpaRepository<Station_Line, Long> {

List<Station_Line> findAllBystationId(long id);
}
