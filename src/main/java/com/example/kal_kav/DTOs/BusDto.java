package com.example.kal_kav.DTOs;

import lombok.*;

@Data
@NoArgsConstructor
// בנאי עם כל השדות
@AllArgsConstructor
public class BusDto {
    private long Id;
    private int Seats;
    private String License_plate;

}
