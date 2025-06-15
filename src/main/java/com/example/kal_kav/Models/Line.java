package com.example.kal_kav.Models;

import java.util.List;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "line")

public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long number;
    private String Sourse;
    private String destination;
    @OneToMany(mappedBy = "line", fetch = FetchType.LAZY)
    private List<Station_Line> station_Lines;
    @Version
    private Long version;

}
