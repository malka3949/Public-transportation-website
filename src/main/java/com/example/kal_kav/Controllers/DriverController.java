package com.example.kal_kav.Controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kal_kav.DTOs.DriverDto;
import com.example.kal_kav.Models.driver;
import com.example.kal_kav.Services.DriverService;



@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/all")
    public ResponseEntity<List<DriverDto>> getAll() {
        return ResponseEntity.ok().body(driverService.getAll());
    }

    @GetMapping("/byId{id}")
    public ResponseEntity<driver> getById(@PathVariable Long id) {
        Optional<driver> driver = driverService.getById(id);
        if (driver.isPresent())
            return ResponseEntity.ok().body(driver.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody DriverDto drive) {
        Long result = driverService.add(drive);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

}
