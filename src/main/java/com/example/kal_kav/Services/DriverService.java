package com.example.kal_kav.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kal_kav.Converters.DriverConverter;
import com.example.kal_kav.DTOs.DriverDto;
import com.example.kal_kav.Models.driver;
import com.example.kal_kav.Repositories.DriverRepository;

@Service
public class DriverService {
    @Autowired
    private DriverConverter driverConverter;
    @Autowired
    private DriverRepository driverRepository;

    public Long add(DriverDto driverdto) {
        driver driver = driverConverter.todriveModel(driverdto);
        if (!driverRepository.exists(Example.of(driver))) {
            return driverRepository.save(driver).getId();
        }
        return null;
    }

    public List<DriverDto> getAll() {
        List<driver>driver= driverRepository.findAll();
        return driverConverter.toDriveDTOsList(driver);
    }

    public Optional<driver> getById(Long id) {
        return driverRepository.findById(id);
    }
}
