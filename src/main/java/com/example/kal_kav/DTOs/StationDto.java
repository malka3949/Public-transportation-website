package com.example.kal_kav.DTOs;

import java.util.List;



//import lombok.AllArgsConstructor;
import lombok.*;
//import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// בנאי עם כל השדות
@AllArgsConstructor
public class StationDto {
    private long id;
    private String name;
    private int number;
    private List<Long> LinesNumber;
}
