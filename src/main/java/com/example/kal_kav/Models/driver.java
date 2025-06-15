package com.example.kal_kav.Models;

import java.util.List;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Data
@Table(name = "driver")

public class driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String Name;
    private String Phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Travel> travels;

    
}