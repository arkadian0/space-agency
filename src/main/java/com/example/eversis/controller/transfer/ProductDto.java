package com.example.eversis.controller.transfer;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {
    private Integer productId;
    private Double coordinateA;
    private Double coordinateB;
    private Double coordinateC;
    private Double coordinateD;
    private BigDecimal price;
    private String productUrl;
    private LocalDateTime acquisitionDate;
}
