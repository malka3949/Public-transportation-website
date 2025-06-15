package com.example.kal_kav.Models;

import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "station")

public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int number;
    @OneToMany(mappedBy = "station", fetch=FetchType.LAZY)
    private List<Station_Line> station_Lines;
    
}
