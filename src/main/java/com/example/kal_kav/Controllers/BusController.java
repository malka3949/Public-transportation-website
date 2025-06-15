package com.example.kal_kav.Controllers;

import java.util.List;
import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kal_kav.DTOs.BusDto;
import com.example.kal_kav.Models.Bus;
import com.example.kal_kav.Services.BusService;


@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/all")
    public ResponseEntity<List<BusDto>> getAll() {
        return ResponseEntity.ok().body(busService.getAll());
    }

    @GetMapping("/byId{id}")
    public ResponseEntity<Bus> getById(@PathVariable Long id) {
        Optional<Bus> course = busService.getById(id);
        if (course.isPresent())
            return ResponseEntity.ok().body(course.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Bus> create(@RequestBody BusDto bus) {
        Bus result = busService.add(bus);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    // @DeleteMapping("/byId/{id}")
    // public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    // if (courseService.deleteById(id))
    // return ResponseEntity.noContent().build();
    // return ResponseEntity.badRequest().build();
    // }

    // @GetMapping("/allByHour/{hour}")
    // public ResponseEntity<List<Course>> getAll(@PathVariable int hour) {
    // return ResponseEntity.ok().body(courseService.getAllInHour(hour));
    // }
}
