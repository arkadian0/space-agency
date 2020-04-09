package com.example.eversis.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "coordinate_a")
    private Double coordinateA;

    @Column(name = "coordinate_b")
    private Double coordinateB;

    @Column(name = "coordinate_c")
    private Double coordinateC;

    @Column(name = "coordinate_d")
    private Double coordinateD;

    public Coordinate() {

    }
    public Coordinate(Double coordinateA, Double coordinateB, Double coordinateC, Double coordinateD) {
        this.coordinateA = coordinateA;
        this.coordinateB = coordinateB;
        this.coordinateC = coordinateC;
        this.coordinateD = coordinateD;
    }

}
