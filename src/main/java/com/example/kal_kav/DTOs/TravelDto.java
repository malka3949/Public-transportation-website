package com.example.kal_kav.DTOs;

import java.sql.Time;

import org.springframework.stereotype.Component;

import lombok.*;

// import lombok.Data;
@NoArgsConstructor
// בנאי עם כל השדות
@AllArgsConstructor
@Data
@Component
public class TravelDto {
    private Long Id;
    private Long busID;
    private Long driverID;
    private Long lineID;
    private Time Departure_time;
}
