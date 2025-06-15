package com.example.kal_kav.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.kal_kav.DTOs.DriverDto;
import com.example.kal_kav.Models.driver;

@Component
public class DriverConverter {

    // private static ModelMapper modelMapper = new ModelMapper();

    // public static DriverDto toDto(Driver driver) {
    // return modelMapper.map(driver, DriverDto.class);
    // }

    // public static Driver toModel(DriverDto driverlDto) {
    // return modelMapper.map(driverlDto, Driver.class);

    // }
    // public static List<Driver> toModelList(List<DriverDto> list) {
    // return list.stream().map(t -> toModel(t)).collect(Collectors.toList());

    // }
    // public static List<DriverDto> toDtoList(List<Driver> list) {
    // return list.stream().map(t -> toDto(t)).collect(Collectors.toList());

    // }

    public DriverDto toDriverDTO(driver Driver) {
        // הוא חלקי DTOההמרה תהיה פשוטה יותר כי אובייקט DTO- בהמרה מאובייקט רגיל ל
        DriverDto driverDTO = new DriverDto();
        driverDTO.setId(Driver.getId());
        driverDTO.setName(Driver.getName());
        driverDTO.setPhone(Driver.getPhone());
        // driverDTO.setTravels(Driver.getTravels());
        return driverDTO;
    }

    public driver todriveModel(DriverDto drivedto) {
        driver driver = new driver();
        // driver.setId(drivedto.getId());
        driver.setName(drivedto.getName());
        driver.setPhone(drivedto.getPhone());
        // driver.setTravels(drivedto.getTravels());

        return driver;

    }

    public List<driver> todriverList(List<DriverDto> driveDtos) {
        return driveDtos.stream()
                .map(d -> todriveModel(d)).collect(Collectors.toList());
    }

    public List<DriverDto> toDriveDTOsList(List<driver> drivers) {
        return drivers.stream()
                .map(d -> toDriverDTO(d)).collect(Collectors.toList());
    }

}
