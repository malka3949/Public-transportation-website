package com.example.kal_kav.Services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kal_kav.Converters.*;
import com.example.kal_kav.DTOs.StationDto;
import com.example.kal_kav.Models.*;
import com.example.kal_kav.Repositories.*;

@Service
public class StationService {
    @Autowired
    private StationConverter stationConverter;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private Station_LineRepository station_LineRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private LineRepository lineRepository;

   
    public Long add(StationDto stationDto) {
        Station station = stationConverter.toStationModel(stationDto);

        // בדוק אם station הוא null
        if (station == null) {
            throw new IllegalArgumentException("Station model cannot be null");
        }

        if (!stationRepository.exists(Example.of(station))) {
            return stationRepository.save(station).getId();
        }
        return null;
    }

    public List<StationDto> getAll() {
        List<Station> stations = stationRepository.findAll();
        return stationConverter.toStationDTOsList(stations);
    }

    public StationDto getById(Long id) {
        Optional<Station> s = stationRepository.findById(id);
        return stationConverter.toStationDto(s.get());
    }

    public Station findByNumber(int number) {
        Station s = stationRepository.findByNumber(number);
        return s;
    }
//לשמיעת כל הנסיעות בתחנה 
    public List<Travel> whenCome(int stationNumber) {
        Station s = stationRepository.findByNumber(stationNumber);
        List<Station_Line> sl = station_LineRepository.findAllBystationId(s.getId());
        List<Travel> travellist = new ArrayList<Travel>();
        List<Travel> t;
        LocalDateTime now = LocalDateTime.now();
        LocalTime HoursBefore = LocalDateTime.now().toLocalTime().minusMinutes(sl.size());
        List<Station_Line> filteredList = s.getStation_Lines().stream()
                .filter(stationLine -> stationLine.getStation().getId() == (s.getId()))
                .collect(Collectors.toList());
        for (int i = 0; i < sl.size(); i++) {
            t = travelRepository.findAllByLineId(sl.get(i).getLine().getId());
            travellist.addAll(t);
        }
        for (int j = 0; j < travellist.size(); j++) {
            Time time = travellist.get(j).getDeparture_time();
            LocalTime departureTime = time.toLocalTime(); // זה אמור לעבוד עכשיו
            long hours = 0;
            long minutes = 0;
            // if (departureTime.isBefore(now.toLocalTime())) {
            // Duration duration = Duration.between(departureTime, now.toLocalTime());
            // hours = (23 - duration.toHours()) + (filteredList.get(0).getStationOrder() /
            // 60);
            // minutes = 60 - (duration.toMinutes() % 60) +
            // (filteredList.get(0).getStationOrder() % 60);
            // }
            if (departureTime.isAfter(now.toLocalTime())
                    || (departureTime.isAfter(HoursBefore) && departureTime.isBefore(now.toLocalTime()))) {

                Duration duration = Duration.between(now.toLocalTime(), departureTime);

                hours = (duration.toHours()) + (filteredList.get(0).getStationOrder() / 60);
                minutes = duration.toMinutes() % 60 + (filteredList.get(0).getStationOrder() % 60) + 1;

                System.out.println("t2:" + travellist.get(j).getLine().getNumber() +
                        ", time:" + departureTime +
                        ", now:" + now.toLocalTime() +
                        ", minutes: " + minutes +
                        ", hours: " + hours);

            }
        }

        return travellist;
    }
//לשמיעת כל הנסיעות של קו מסוים בתחנה
    public List<Travel> whenComeLine(int stationNumber, Long number) {

        Station s = stationRepository.findByNumber(stationNumber);
        if (s == null) {
            throw new IllegalArgumentException("Station with number " + stationNumber + " not found.");
        }
    
        Line l = lineRepository.findByNumber(number);
        if (l == null) {
            throw new IllegalArgumentException("Line with number " + number + " not found.");
        }

        List<Station_Line> filteredList = l.getStation_Lines().stream()
                .filter(stationLine -> stationLine.getStation().getId() == (s.getId()))
                .collect(Collectors.toList());

        System.out.println("l:" + l.getId());
        List<Travel> travellist = new ArrayList<Travel>();
        LocalDateTime now = LocalDateTime.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        travellist = travelRepository.findAllByLineId(l.getId());
        for (int j = 0; j < travellist.size(); j++) {
            Time time = travellist.get(j).getDeparture_time();
            LocalTime departureTime = time.toLocalTime(); // זה אמור לעבוד עכשיו
            long hours = 0;
            long minutes = 0;
            if (departureTime.isBefore(now.toLocalTime())) {
                Duration duration = Duration.between(departureTime, now.toLocalTime());
                hours = (23 - duration.toHours()) + (filteredList.get(0).getStationOrder() / 60);
                minutes = 60 - (duration.toMinutes() % 60) + (filteredList.get(0).getStationOrder() % 60);
            } else if (departureTime.isAfter(now.toLocalTime())) {
                Duration duration = Duration.between(now.toLocalTime(), departureTime);

                hours = (duration.toHours()) + (filteredList.get(0).getStationOrder() / 60);
                minutes = duration.toMinutes() % 60 + (filteredList.get(0).getStationOrder() % 60) + 1;
            }
            System.out.println("t2:" + travellist.get(j).getLine().getNumber() +
                    ", time:" + departureTime +
                    ", now:" + now.toLocalTime() +
                    ", minutes: " + minutes +
                    ", hours: " + hours);
        }

        return travellist;
    }

}
