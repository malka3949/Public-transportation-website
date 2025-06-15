package com.example.kal_kav.Converters;
import java.util.stream.Collectors;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.example.kal_kav.DTOs.LineDto;
import com.example.kal_kav.Models.Line;
import com.example.kal_kav.Models.Station;
import com.example.kal_kav.Models.Station_Line;
import com.example.kal_kav.Repositories.StationRepository;
import com.example.kal_kav.Repositories.Station_LineRepository;


@Component
public class LineConverter {
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private Station_LineRepository station_LineRepository;

    public LineDto toLineDto(Line line) {
        LineDto lineDto = new LineDto();
        lineDto.setId(line.getId());
        lineDto.setNumber(line.getNumber());
        lineDto.setDestination(line.getDestination());
        lineDto.setSourse(line.getSourse());
        lineDto.setStationsId(
                line.getStation_Lines().stream()
                        .map(stationLine -> stationLine.getStation().getId())
                        .collect(Collectors.toList()));
        return lineDto;
    }

    public Line toLineModel(LineDto linedto) {
        Line line = new Line();
        line.setNumber(linedto.getNumber());
        line.setDestination(linedto.getDestination());
        line.setSourse(linedto.getSourse());

        
       
        AtomicInteger x = new AtomicInteger(1);
        List<Station_Line> station_Lines = new ArrayList<>();
        linedto.getStationsId().forEach(s -> {
            Optional<Station> optionalStation = stationRepository.findById(s);
            if (optionalStation.isPresent()) {
                Station st = optionalStation.get();
                Station_Line sl = new Station_Line();
                sl.setLine(line); // קישור ל-Line
                sl.setStation(st);
                sl.setStationOrder(x.get());
                station_Lines.add(sl);
                x.incrementAndGet();
                if (!station_LineRepository.exists(Example.of(sl))) {
                    station_LineRepository.save(sl);
                    st.getStation_Lines().add(sl);
                    // שמור את Station_Line
                    System.out.println("save stationLimne");
                }
            } else {
                System.out.println("Station with ID " + s + " not found.");
            }
        });

        line.setStation_Lines(station_Lines); // הוסף את Station_Line לרשימה

        return line;
    }

    
    public List<Line> toBusList(List<LineDto> LineDtos) {
        return LineDtos.stream()
                .map(l -> toLineModel(l)).collect(Collectors.toList());
    }

    public List<LineDto> toLineDTOsList(List<Line> Lines) {
        return Lines.stream()
                .map(l -> toLineDto(l)).collect(Collectors.toList());
    }

}
