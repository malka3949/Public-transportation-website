package com.example.kal_kav.Models;

import java.sql.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus._id")
    private Bus bus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver._id")
    private driver driver;
    @ManyToOne
    @JoinColumn(name = "line._id")
    private Line line;
    private Time Departure_time;
}
