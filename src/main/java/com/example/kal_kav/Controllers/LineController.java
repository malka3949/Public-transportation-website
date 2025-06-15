package com.example.kal_kav.Controllers;import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kal_kav.Converters.LineConverter;
import com.example.kal_kav.DTOs.LineDto;
import com.example.kal_kav.Models.Line;
import com.example.kal_kav.Services.LineService;

@RestController
@RequestMapping("/line")
public class LineController {
    @Autowired
    private LineService lineService;
    @Autowired
    private LineConverter lineConverter;

    @GetMapping("/all")
    public ResponseEntity<List<LineDto>> getAll() {
        return ResponseEntity.ok().body(lineService.getAll());
    }

    // @GetMapping("/byId{id}")
    // public ResponseEntity<Line> getById(@PathVariable Long id) {
    // LineDto line = lineService.getById(id);
    // if (line)
    // return ResponseEntity.ok().body(line);
    // return ResponseEntity.notFound().build();
    // }
    @GetMapping("/byId{id}")
    public ResponseEntity<Line> getById(@PathVariable Long id) {
        LineDto lineDto = lineService.getById(id);
        if (lineDto != null) {
            Line line = lineConverter.toLineModel(lineDto); // המרה מ-LineDto ל-Line
            return ResponseEntity.ok().body(line);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody LineDto line) {
        Long result = lineService.add(line);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addstationtoline")

    public Long addStationToLine(Long stationid, Long lineid, int stationOrder) {
        return lineService.addStationToLine(stationid, lineid, stationOrder);

    }

    @DeleteMapping("/deletestationtoline")

    public Long deleteStationToLine(Long stationid, Long lineid) {
        return lineService.deletStationToLine(stationid, lineid);

    }

    @GetMapping("/namesStation")
    public List<String> AllStatiosnLine(Long Linenumber) {
        List<String> s = lineService.AllStatiosnLine(Linenumber);
        return s;
    }

    @GetMapping("/alltimes")
    public List<String> getAlllineOnTime(Long Linenumber) {
        List<String> s = lineService.getAlllineTime(Linenumber);
        return s;
    }

    @GetMapping("/times")
    public List<String> getOnTime(Long Linenumber,String referenceTime) {
        List<String> s = lineService.getOnTime(Linenumber, referenceTime);
        return s;
    }
    @GetMapping("/WhereNow")
    public List<String> WheretheBusNow(Long lineNumber) {
        List<String> s = lineService.WheretheBusNow(lineNumber);
        return s;
    }

}
