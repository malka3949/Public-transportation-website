package com.example.kal_kav.Converters;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kal_kav.DTOs.StationDto;
import com.example.kal_kav.Models.Station;
import com.example.kal_kav.Repositories.StationRepository;



@Component
public class StationConverter {
   
    @Autowired
    private StationRepository stationRepository;

    public StationDto toStationDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        stationDto.setNumber(station.getNumber());
        stationDto.setLinesNumber(station.getStation_Lines().stream()
                .map(s -> s.getLine().getNumber()).collect(Collectors.toList()));

        return stationDto;
    }

    public Station toStationModel(StationDto stationdto) {
        Station station = new Station();
        station.setName(stationdto.getName());
        station.setNumber(stationdto.getNumber());
        stationRepository.save(station);
        return station;
    }

    
    public List<Station> toStationList(List<StationDto> StationDtos) {
        return StationDtos.stream()
                .map(s -> toStationModel(s)).collect(Collectors.toList());
    }

    public List<StationDto> toStationDTOsList(List<Station> stations) {
        return stations.stream()
                .map(s -> toStationDto(s)).collect(Collectors.toList());
    }

}
