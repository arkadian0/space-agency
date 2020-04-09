package com.example.eversis.controller.transfer;

import com.example.eversis.enums.ImageryType;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class MissionDto {
    @NonNull
    private String name;
    private ImageryType imageryType;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;

}
