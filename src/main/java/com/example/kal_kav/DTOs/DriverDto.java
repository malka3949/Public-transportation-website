package com.example.kal_kav.DTOs;

import lombok.*;


@Data
@NoArgsConstructor
// בנאי עם כל השדות
@AllArgsConstructor
public class DriverDto {
    private long Id;
    private String Name;
    private String Phone;
    
}
