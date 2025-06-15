package com.example.kal_kav.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "station_line")
// @IdClass(Station_lines_id.class)

public class Station_Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id", nullable = false) // ודא שהעמודה לא יכולה להיות null
    private Line line;
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "line_id")
    // private Line line;
    private int stationOrder;

}
