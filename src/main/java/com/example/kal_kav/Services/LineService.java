package com.example.kal_kav.Services;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kal_kav.Converters.*;
import com.example.kal_kav.DTOs.LineDto;
import com.example.kal_kav.Models.*;
import com.example.kal_kav.Repositories.*;



@Service
public class LineService {
    @Autowired
    private LineConverter lineConverter;
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private Station_LineRepository station_LineRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private StationRepository stationRepository;

    public Long add(LineDto linedto) {
        Line line = lineConverter.toLineModel(linedto);
        if (!lineRepository.exists(Example.of(line))) {
            return lineRepository.save(line).getId();
        }
        return null;
    }

    public List<LineDto> getAll() {
        List<Line> lines = lineRepository.findAll();
        return lineConverter.toLineDTOsList(lines);
    }

    public LineDto getById(Long id) {
        Optional<Line> line = lineRepository.findById(id);
        Line l = line.get();
        return lineConverter.toLineDto(l);
    }

    // הוספת תחנה לקו
    public Long addStationToLine(Long stationid, Long lineid, int stationOrder) {

        Station stationModel = stationRepository.getById(stationid);
        Line lineModel = lineRepository.getById(lineid);
        Station_Line s = new Station_Line();
        s.setLine(lineModel);
        s.setStation(stationModel);
        s.setStationOrder(stationOrder);
        for (int i = 0; i < lineModel.getStation_Lines().size() && i < stationOrder; i++) {
        }
        for (int i = stationOrder; i < lineModel.getStation_Lines().size(); i++) {
            lineModel.getStation_Lines().get(i)
                    .setStationOrder(lineModel.getStation_Lines().get(i).getStationOrder() + 1);
        }
        if (stationModel.getStation_Lines() == null) {
            stationModel.setStation_Lines(new ArrayList<>());
        }
        stationModel.getStation_Lines().add(s);

        station_LineRepository.save(s);
        stationRepository.save(stationModel);
        lineRepository.save(lineModel);

        return lineModel.getNumber();
    }

    // מחיקת תחנה מקו
    public Long deletStationToLine(Long stationid, Long lineid) {

        Station stationModel = stationRepository.getById(stationid);
        Line lineModel = lineRepository.getById(lineid);
        Station_Line s = new Station_Line();
        for (int i = 0; i < lineModel.getStation_Lines().size(); i++) {

            if (lineModel.getStation_Lines().get(i).getLine().getId() == lineid
                    && lineModel.getStation_Lines().get(i).getStation().getId() == stationid) {
                s = lineModel.getStation_Lines().get(i);
            }

        }

        for (int i = s.getStationOrder(); i < lineModel.getStation_Lines().size(); i++) {
            lineModel.getStation_Lines().get(i)
                    .setStationOrder(lineModel.getStation_Lines().get(i).getStationOrder() - 1);
        }

        lineModel.getStation_Lines().remove(s);
        station_LineRepository.delete(s);
        stationModel.getStation_Lines().remove(s);

        return lineModel.getNumber();
    }

    // לשמיעת כל תחנות הקו
    public List<String> AllStatiosnLine(Long number) {

        Line l = lineRepository.findByNumber(number);
        List<Station_Line> s = l.getStation_Lines();
        List<String> stName = new ArrayList<String>();
        for (int i = 0; i < s.size(); i++) {
            stName.add(s.get(i).getStation().getName());
        }
        return stName;
    }

    // לשמיעת כל נסיעות הקו
    public List<String> getAlllineTime(Long number) {
        List<String> s = new ArrayList<String>();
        Line l = lineRepository.findByNumber(number);
        List<Travel> travellist = new ArrayList<Travel>();
        travellist = travelRepository.findAllByLineId(l.getId());
        for (int i = 0; i < travellist.size(); i++) {
            s.add(travellist.get(i).getDeparture_time().toString());

        }
        return s;

    }

    // פונקציה לקבלת הנסיעות בתווב הקרות
    public List<String> getOnTime(Long number, String referenceTime1) {
        LocalTime referenceTime = LocalTime.parse(referenceTime1, DateTimeFormatter.ISO_LOCAL_TIME);
        List<String> s = new ArrayList<String>();
        Line l = lineRepository.findByNumber(number);
        List<Travel> travellist = travelRepository.findAllByLineId(l.getId());
        LocalTime twoHoursBefore = referenceTime.minusHours(2);
        LocalTime twoHoursAfter = referenceTime.plusHours(2);

        for (Travel travel : travellist) {
            Time departureTime = travel.getDeparture_time();
            if (departureTime != null) {
                // המרה מ-Time ל-LocalDateTime
                LocalTime localDateTime = departureTime.toLocalTime(); // יש צורך להמיר עם תאריך

                if (localDateTime.isAfter(twoHoursBefore) && localDateTime.isBefore(twoHoursAfter)) {
                    s.add(localDateTime.toString());
                }
            }
        }
        return s; // החזרת הרשימה מחוץ ללולאה
    }

    // פונקציה לשמיעת אוטובוס לאורך כל ציר הנסיעה
    public List<String> WheretheBusNow(Long number) {
        List<String> s = new ArrayList<String>() {

        };
        Line l = lineRepository.findByNumber(number);
        int stationSize = l.getStation_Lines().size();
        List<Travel> travelList = travelRepository.findAllByLineId(l.getId());
        LocalDateTime now = LocalDateTime.now();
        LocalTime twoHoursBefore = LocalDateTime.now().toLocalTime().minusMinutes(stationSize);

        for (Travel travel : travelList) {
            Time departureTime = travel.getDeparture_time();
            if (departureTime != null) {
                // המרה מ-Time ל-LocalDateTime
                LocalTime localDateTime = departureTime.toLocalTime(); // יש צורך להמיר עם תאריך

                if (localDateTime.isAfter(twoHoursBefore) && localDateTime.isBefore(now.toLocalTime())) {
                    long hoursDifference = Duration.between(departureTime.toLocalTime(), now).toHours();
                    long time = hoursDifference * 60
                            + (Duration.between(departureTime.toLocalTime(), now).toMinutes() % 60);
                    var stationName = "";
                    for (int i = 0; i < l.getStation_Lines().size(); i++) {
                        if (l.getStation_Lines().get(i).getStationOrder() == time) {

                            Station st = l.getStation_Lines().get(i).getStation();
                            stationName = st.getName();
                        }
                    }
                    System.out.println("now: " + now + " departureTime: " + departureTime + " minutes: " + time
                            + " stationName: " + stationName);
                    s.add(stationName + ",");
                }
            }
        }
        return s; // החזר רשימה ריקה או את התוצאה הרצויה
    }
}
