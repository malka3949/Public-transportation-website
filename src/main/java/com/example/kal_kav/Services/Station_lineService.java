package com.example.kal_kav.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.kal_kav.Models.Station_Line;
import com.example.kal_kav.Repositories.*;


@Service
public class Station_lineService {
    @Autowired

    private Station_LineRepository station_LineRepository;

    
    public List<Station_Line> getAll() {

        return station_LineRepository.findAll();
    }

}
