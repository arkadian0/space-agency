package com.example.eversis.model;

import com.example.eversis.controller.transfer.ProductDto;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coordinate_id")
    private Coordinate footPrint;

    @Column
    private BigDecimal price;

    @Column(name = "product_url")
    private String productUrl;

    @Column(name = "acquisition_date")
    LocalDateTime acquisitionDate;

    @ManyToOne
    Mission mission;

    public Product() {
    }

    public Product(Coordinate footPrint, BigDecimal price, String productUrl, LocalDateTime acquisitionDate, Mission mission) {
        this.footPrint = footPrint;
        this.price = price;
        this.productUrl = productUrl;
        this.acquisitionDate = acquisitionDate;
        this.mission = mission;
    }

    private Product(ProductDto productDto, Mission mission) {
        this.footPrint = new Coordinate(productDto.getCoordinateA(),productDto.getCoordinateB(),productDto.getCoordinateC(),productDto.getCoordinateD());
        this.price = productDto.getPrice();
        this.productUrl = productDto.getProductUrl();
        this.acquisitionDate = productDto.getAcquisitionDate();
        this.mission = mission;
    }

    public static List<Product> of(List<ProductDto> productDtos, Mission mission) {
        return productDtos.stream().map(productDto -> new Product(productDto, mission)).collect(Collectors.toList());
    }

}

