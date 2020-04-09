package com.example.eversis.service.product;

import com.example.eversis.controller.transfer.OrderProductDto;
import com.example.eversis.controller.transfer.ProductDto;
import com.example.eversis.controller.transfer.SearchProductDto;
import com.example.eversis.dao.MissionRepository;
import com.example.eversis.dao.OrderRepository;
import com.example.eversis.dao.ProductRepository;
import com.example.eversis.dao.UserRepository;
import com.example.eversis.exceptions.MissionNotFoundException;
import com.example.eversis.exceptions.ProductNotFoundExceptions;
import com.example.eversis.exceptions.UserNotFoundException;
import com.example.eversis.model.*;
import com.example.eversis.service.mapper.ProductDtoMapper;
import com.example.eversis.service.product.port.ProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
class ProductImageServiceImpl implements ProductServicePort {

    private final ProductRepository productRepository;
    private final MissionRepository missionRepository;

    @Override
    public List<Integer> save(List<ProductDto> productDtos, String missionId) {
        List<Product> products = null;
        Optional<Mission> mission = missionRepository.findById(missionId);

        if (mission.isPresent()) {
            products = productRepository.saveAll(Product.of(productDtos, mission.get()));
        } else
            throw new MissionNotFoundException(String.format("Mission with id : %s not found", missionId));

        return products.stream().map(Product::getId).collect(Collectors.toList());
    }

    @Override
    public void remove(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDto> searchProducts(SearchProductDto searchProductDto) {
        switch (searchProductDto.getSearchDateType()) {
            case LOWER_THAN:
                return searchProductsLowerThanGivenAcquisitionDate(searchProductDto);
            case GREATER_THAN:
                return searchProductsGreaterThanGivenAcquisitionDate(searchProductDto);
            case BETWEEN:
                return searchProductsBetweenGivenAcquisitionDates(searchProductDto);
            default:
                throw new IllegalStateException(String.format("Search with type : %s not found", searchProductDto.getSearchDateType()));
        }
    }

    private List<ProductDto> searchProductsLowerThanGivenAcquisitionDate(SearchProductDto searchProductDto) {
        List<Product> products = productRepository.findProductsByDateLowerThanAcquisitionDate(searchProductDto);
        return createParsedProductOrEmptyList(products);
    }

    private List<ProductDto> searchProductsGreaterThanGivenAcquisitionDate(SearchProductDto searchProductDto) {
        List<Product> products = productRepository.findProductsByDateGraterThanAcquisitionDate(searchProductDto);
        return createParsedProductOrEmptyList(products);
    }

    private List<ProductDto> searchProductsBetweenGivenAcquisitionDates(SearchProductDto searchProductDto) {
        List<Product> products = productRepository.findProductsByDateBetweenAcquisitionDate(searchProductDto);
        return createParsedProductOrEmptyList(products);
    }


    private List<ProductDto> createParsedProductOrEmptyList(List<Product> products) {
        if (!products.isEmpty())
            return ProductDtoMapper.map(products);
        else
            return Collections.emptyList();
    }


}
