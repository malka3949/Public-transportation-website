package com.example.kal_kav.Controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kal_kav.DTOs.TravelDto;
import com.example.kal_kav.Models.Travel;
import com.example.kal_kav.Services.TravelServices;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Travel")
public class TravelController {
    @Autowired
    private TravelServices travelServices;

    @GetMapping("/all")
    public ResponseEntity<List<TravelDto>> getAll() {
        return ResponseEntity.ok().body(travelServices.getAll());
    }

    @GetMapping("/byId{id}")
    public ResponseEntity<Travel> getById(@PathVariable Long id) {
        Optional<Travel> travel = travelServices.getById(id);
        if (travel.isPresent())
            return ResponseEntity.ok().body(travel.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody TravelDto travel) {
        Long result = travelServices.add(travel);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

}
