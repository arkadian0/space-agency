package com.example.eversis.controller;

import com.example.eversis.controller.transfer.OrderProductDto;
import com.example.eversis.controller.transfer.ProductDto;
import com.example.eversis.controller.transfer.SearchProductDto;
import com.example.eversis.service.order.port.OrderServicePort;
import com.example.eversis.service.product.port.ProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductServicePort productServicePort;
    private final OrderServicePort orderServicePort;

    @PostMapping(value = "/mission/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Integer>> addProduct(@RequestBody List<ProductDto> productDtos, @PathVariable(value = "id") String missionId) {
        productServicePort.save(productDtos, missionId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Integer productId) {
        productServicePort.remove(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<ProductDto>> searchProduct(@RequestBody SearchProductDto searchProductDto) {
        List<ProductDto> productDtos = productServicePort.searchProducts(searchProductDto);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/order/{username}", consumes = "application/json")
    public ResponseEntity<String> createOrders(@RequestBody List<OrderProductDto> productDtos, @PathVariable(value = "username") String userName) {
        orderServicePort.createOrders(productDtos, userName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
