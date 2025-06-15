package com.example.kal_kav.Converters;

// import com.example.lesson2.DTOs.BusDto;

// import com.example.lesson2.Models.Bus;
// import com.example.lesson2.Repositories.BusRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kal_kav.DTOs.BusDto;
import com.example.kal_kav.Models.Bus;
import com.example.kal_kav.Repositories.BusRepository;
@Component

public class BusConverter {
    
    @Autowired
    private BusRepository busRepository;
    public  BusDto toBusDTO(Bus bus) {
        // הוא חלקי DTOההמרה תהיה פשוטה יותר כי אובייקט DTO- בהמרה מאובייקט רגיל ל
        BusDto busDTO = new BusDto();
        busDTO.setId(bus.getId());
        busDTO.setLicense_plate(bus.getLicense_plate());
        busDTO.setSeats(bus.getSeats());
        return busDTO;
    }

    // public static Bus toBusModel(BusDto busDTO) {
    // Bus bus = new Bus();
    // bus.setId(busDTO.getId());
    // bus.setLicense_plate(busDTO.getLicense_plate());
    // bus.setSeats(busDTO.getSeats());
    // return bus;

    // }
    public  Bus toModeBus(BusDto busDto) {
        Bus bus3 = new Bus();
        // bus3.setId(busDto.getId());
        bus3.setLicense_plate(busDto.getLicense_plate());
        bus3.setSeats(busDto.getSeats());
        busRepository.save(bus3);
        return bus3;

    }

    public  List<Bus> toBusList(List<BusDto> BusDtos) {
        return BusDtos.stream()
                .map(b -> toModeBus(b)).collect(Collectors.toList());
    }

    public  List<BusDto> toBusDtoList(List<Bus> Buses) {
        return Buses.stream()
                .map(b -> toBusDTO(b)).collect(Collectors.toList());
    }
}
