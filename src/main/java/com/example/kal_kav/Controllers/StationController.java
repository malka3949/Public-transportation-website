package com.example.kal_kav.Controllers;import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kal_kav.Converters.StationConverter;
import com.example.kal_kav.DTOs.*;
import com.example.kal_kav.Models.*;
import com.example.kal_kav.Services.*;



@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;
    @Autowired
    private Station_lineService station_lineService;
    @Autowired
    private StationConverter stationConverter;

    @GetMapping("/all")
    public ResponseEntity<List<StationDto>> getAll() {
        return ResponseEntity.ok().body(stationService.getAll());
    }

    @GetMapping("/Station_Lines")
    public ResponseEntity<List<Station_Line>> getAllStationLines() {
        return ResponseEntity.ok().body(station_lineService.getAll());
    }

    @GetMapping("/byId{id}")
    public ResponseEntity<Station> getById(@PathVariable Long id) {
        StationDto station = stationService.getById(id);
        if (station != null) {
            Station s = stationConverter.toStationModel(station);
            return ResponseEntity.ok().body(s);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody StationDto station) {
        Long result = stationService.add(station);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getalllines")
    public int whenCome(int stationNumber) {
        List<Travel> s = stationService.whenCome(stationNumber);
        return s.size();

    }

    @GetMapping("/getlineTimes")

    public int whenComeLine(int stationNumber, Long linenumber) {
        List<Travel> s = stationService.whenComeLine(stationNumber, linenumber);
        return s.size();
    }
}