package com.example.kal_kav.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kal_kav.Converters.*;
import com.example.kal_kav.DTOs.TravelDto;
import com.example.kal_kav.Models.Travel;
import com.example.kal_kav.Repositories.*;


import java.util.List;
import java.util.Optional;

@Service
public class TravelServices {
    @Autowired

    private TravelRepository travelRepository;

    @Autowired
    private TravelConverter travelConverter;

    public Long add(TravelDto travelDto) {
        Travel travel = travelConverter.toTravelModel(travelDto);
        if (!travelRepository.exists(Example.of(travel))) {
            return travelRepository.save(travel).getId();
        }
        return travel.getId();
    }

    public List<TravelDto> getAll() {
        List<Travel> travels=travelRepository.findAll();
       return travelConverter.toTravelDTOsList(travels);
    }

    public Optional<Travel> getById(Long id) {
        return travelRepository.findById(id);
    }

    // 

}
