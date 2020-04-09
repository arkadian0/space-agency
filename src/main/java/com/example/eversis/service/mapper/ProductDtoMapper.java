package com.example.eversis.service.mapper;

import com.example.eversis.controller.transfer.ProductDto;
import com.example.eversis.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDtoMapper {

    public static List<ProductDto> map(List<Product> productList) {
        return productList.stream()
                .map(ProductDtoMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    private static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .acquisitionDate(product.getAcquisitionDate())
                .coordinateA(product.getFootPrint().getCoordinateA())
                .coordinateB(product.getFootPrint().getCoordinateB())
                .coordinateC(product.getFootPrint().getCoordinateC())
                .coordinateD(product.getFootPrint().getCoordinateD())
                .price(product.getPrice())
                .productId(product.getId())
                .productUrl(product.getProductUrl()).build();
    }
}
