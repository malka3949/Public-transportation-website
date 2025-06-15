package com.example.kal_kav.DTOs;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
// בנאי עם כל השדות
@AllArgsConstructor
public class LineDto {
    private long Id;
    private Long Number;
    private String Sourse;
    private String destination;
    private List<Long> stationsId;
    

}
