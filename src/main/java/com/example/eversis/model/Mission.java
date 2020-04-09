package com.example.eversis.model;

import com.example.eversis.controller.transfer.MissionDto;
import com.example.eversis.enums.ImageryType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Mission {

    @Id
    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "imagery_type", length = 15)
    private ImageryType imageryType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mission_name")
    private List<Product> products;

    public Mission() {

    }

    public Mission(String name, ImageryType imageryType, LocalDateTime startDate, LocalDateTime finishDate) {
        this.name = name;
        this.finishDate = finishDate;
        this.startDate = startDate;
        this.imageryType = imageryType;
    }

    public Mission(MissionDto missionDto) {
        this.name = missionDto.getName();
        this.finishDate = missionDto.getFinishDate();
        this.startDate = missionDto.getStartDate();
        this.imageryType = missionDto.getImageryType();
    }

    public static Mission of(MissionDto missionDto) {
        return new Mission(missionDto);
    }
}
