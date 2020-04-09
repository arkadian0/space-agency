package com.example.eversis.controller.transfer;

import com.example.eversis.enums.ImageryType;
import com.example.eversis.enums.SearchDateType;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class SearchProductDto {
    @NonNull
    private String missionName;
    @NonNull
    private ImageryType imageryType;
    @NonNull
    private LocalDateTime acquisitionDateFrom;
    private LocalDateTime acquisitionDateTo;
    @NonNull
    private SearchDateType searchDateType;
}
