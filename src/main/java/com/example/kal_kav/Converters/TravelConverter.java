package com.example.kal_kav.Converters;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kal_kav.DTOs.TravelDto;
import com.example.kal_kav.Models.Travel;
import com.example.kal_kav.Repositories.BusRepository;
import com.example.kal_kav.Repositories.DriverRepository;
import com.example.kal_kav.Repositories.LineRepository;

@Component
public class TravelConverter {
     @Autowired 
private  BusRepository busRepository;
@Autowired 
private  LineRepository lineRepository;
@Autowired 
private  DriverRepository driverRepository;
    
    public TravelDto toTravelDto(Travel travel) {
        TravelDto travelDto = new TravelDto();
    
        travelDto.setId(travel.getId());
        if (travel.getBus() != null) {
            travelDto.setBusID(travel.getBus().getId());
        } else {
            // טיפול במקרה שה-Bus הוא null
        }
    
        travelDto.setDeparture_time(travel.getDeparture_time());
    
        if (travel.getDriver() != null) {
            travelDto.setDriverID(travel.getDriver().getId());
        } else {
            // טיפול במקרה שה-Driver הוא null
        }
    
        if (travel.getLine() != null) {
            travelDto.setLineID(travel.getLine().getId());
        } else {
            // טיפול במקרה שה-Line הוא null
        }
    
        return travelDto;
    }
    
    public  Travel toTravelModel(TravelDto travelndto) {
        Travel travel = new Travel();
       // travel.setId(travelndto.getId());
        travel.setBus(busRepository.findById(travelndto.getBusID()).orElse(null));
        travel.setDeparture_time(travelndto.getDeparture_time());
        travel.setDriver(driverRepository.findById(travelndto.getDriverID()).orElse(null));
        travel.setLine(lineRepository.findById(travelndto.getLineID()).orElse(null));
        return travel;
    }

    public  List<Travel> toTravelList(List<TravelDto> TravelDtos) {
        return TravelDtos.stream()
                .map(t -> toTravelModel(t)).collect(Collectors.toList());
    }

    public  List<TravelDto> toTravelDTOsList(List<Travel> Travels) {
        return Travels.stream()
                .map(t -> toTravelDto(t)).collect(Collectors.toList());
    }

}
