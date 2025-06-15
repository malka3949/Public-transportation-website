package com.example.kal_kav.Models;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

import lombok.*;
@Component
@Entity
@Data
@Table(name = "bus")

public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private int Seats;
    private String License_plate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bus", fetch = FetchType.LAZY)
    public List<Travel> travels;
}



