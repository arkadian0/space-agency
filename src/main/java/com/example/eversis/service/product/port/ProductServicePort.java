package com.example.eversis.service.product.port;

import com.example.eversis.controller.transfer.ProductDto;
import com.example.eversis.controller.transfer.SearchProductDto;

import java.util.List;

public interface ProductServicePort {
    List<Integer> save(List<ProductDto> productDto, String missionId);

    void remove(Integer productId);

    List<ProductDto> searchProducts(SearchProductDto searchProductDto);
}
