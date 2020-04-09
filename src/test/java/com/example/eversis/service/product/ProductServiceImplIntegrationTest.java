package com.example.eversis.service.product;

import com.example.eversis.dao.MissionRepository;
import com.example.eversis.dao.ProductRepository;
import com.example.eversis.enums.ImageryType;
import com.example.eversis.enums.SearchDateType;
import com.example.eversis.model.Coordinate;
import com.example.eversis.model.Mission;
import com.example.eversis.model.Product;
import com.example.eversis.controller.transfer.ProductDto;
import com.example.eversis.controller.transfer.SearchProductDto;
import com.example.eversis.service.product.port.ProductServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductServiceImplIntegrationTest {

    private LocalDateTime fakeLocalDateTime = LocalDateTime.of(1999, 2, 2, 1, 1);
    private Mission mission1 = new Mission("added1", ImageryType.PANCHROMATIC, fakeLocalDateTime, fakeLocalDateTime.plusDays(10));
    private Mission mission2 = new Mission("added2", ImageryType.PANCHROMATIC, fakeLocalDateTime, fakeLocalDateTime.plusDays(10));
    private Coordinate footPrint = new Coordinate(2.25, 3.45, 4.35, 2.12);
    private Product product1 = new Product(footPrint, new BigDecimal(200), "url", fakeLocalDateTime, mission1);
    private Product product2 = new Product(footPrint, new BigDecimal(200), "url", fakeLocalDateTime.plusDays(10), mission1);
    private Product product3 = new Product(footPrint, new BigDecimal(200), "url", fakeLocalDateTime.minusDays(10), mission1);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private ProductServicePort sut;

    @BeforeEach
    void setUp() {
        missionRepository.saveAll(List.of(mission1, mission2));
        productRepository.saveAll(List.of(product1, product2, product3));
    }


    @Test
    void check_correct_save_of_product() {
        //given
        final String missionId = "added1";
        ProductDto productDto = ProductDto.builder().coordinateA(2.50)
                .coordinateB(2.50).coordinateC(2.50).coordinateD(2.50)
                .price(new BigDecimal(2000))
                .productUrl("url")
                .acquisitionDate(fakeLocalDateTime)
                .build();


        //when
        List<Integer> ids = sut.save(List.of(productDto), missionId);

        //then
        Optional<Product> product = productRepository.findById(ids.get(0));
        assertTrue(product.isPresent());
        assertEquals(product.get().getFootPrint().getCoordinateA(), productDto.getCoordinateA());
        assertEquals(product.get().getFootPrint().getCoordinateB(), productDto.getCoordinateB());
        assertEquals(product.get().getFootPrint().getCoordinateB(), productDto.getCoordinateB());
        assertEquals(product.get().getFootPrint().getCoordinateB(), productDto.getCoordinateB());
        assertEquals(product.get().getAcquisitionDate(), productDto.getAcquisitionDate());
        assertTrue(product.get().getPrice().compareTo(productDto.getPrice()) == 0);
        assertEquals(product.get().getProductUrl(), productDto.getProductUrl());

    }

    @Test
    void return_correct_products_when_schearch_type_is_lower_than() {
        //given
        SearchProductDto searchProductDto = SearchProductDto.builder()
                .imageryType(ImageryType.PANCHROMATIC)
                .missionName("added1")
                .searchDateType(SearchDateType.LOWER_THAN)
                .acquisitionDateFrom(fakeLocalDateTime.plusDays(20)).build();

        //when
        List<ProductDto> productDtos = sut.searchProducts(searchProductDto);

        // then
        assertEquals(productDtos.size(), 3);

    }

    @Test
    void return_correct_products_when_schearch_type_is_greater_than() {
        //given
        SearchProductDto searchProductDto = SearchProductDto.builder()
                .imageryType(ImageryType.PANCHROMATIC)
                .missionName("added1")
                .searchDateType(SearchDateType.GREATER_THAN)
                .acquisitionDateFrom(fakeLocalDateTime).build();

        //when
        List<ProductDto> productDtos = sut.searchProducts(searchProductDto);

        // then
        assertEquals(productDtos.size(), 1);

    }

    @Test
    void return_correct_products_when_schearch_type_is_between() {
        //given
        SearchProductDto searchProductDto = SearchProductDto.builder()
                .imageryType(ImageryType.PANCHROMATIC)
                .missionName("added1")
                .searchDateType(SearchDateType.BETWEEN)
                .acquisitionDateFrom(fakeLocalDateTime.minusDays(5))
                .acquisitionDateTo(fakeLocalDateTime.plusDays(20))
                .build();

        //when
        List<ProductDto> productDtos = sut.searchProducts(searchProductDto);

        // then
        assertEquals(productDtos.size(), 2);
    }

    @Test
    void return_empty_list_when_products_notFound_for_search_type_lower_than() {
        //given
        SearchProductDto searchProductDto = SearchProductDto.builder()
                .imageryType(ImageryType.MULTISPECTRAL)
                .missionName("added1")
                .searchDateType(SearchDateType.LOWER_THAN)
                .acquisitionDateFrom(fakeLocalDateTime.plusDays(20)).build();

        //when
        List<ProductDto> productDtos = sut.searchProducts(searchProductDto);

        // then
        assertEquals(productDtos.size(), 0);
    }

    @Test
    void return_empty_list_when_products_notFound_for_search_type_greater_than() {
        //given
        SearchProductDto searchProductDto = SearchProductDto.builder()
                .imageryType(ImageryType.MULTISPECTRAL)
                .missionName("added1")
                .searchDateType(SearchDateType.GREATER_THAN)
                .acquisitionDateFrom(fakeLocalDateTime).build();

        //when
        List<ProductDto> productDtos = sut.searchProducts(searchProductDto);

        // then
        assertEquals(productDtos.size(), 0);
    }

    @Test
    void return_empty_list_when_products_notFound_for_search_type_between() {
        //given
        SearchProductDto searchProductDto = SearchProductDto.builder()
                .imageryType(ImageryType.MULTISPECTRAL)
                .missionName("added1")
                .searchDateType(SearchDateType.BETWEEN)
                .acquisitionDateFrom(fakeLocalDateTime.minusDays(5))
                .acquisitionDateTo(fakeLocalDateTime.plusDays(20))
                .build();

        //when
        List<ProductDto> productDtos = sut.searchProducts(searchProductDto);

        // then
        assertEquals(productDtos.size(), 0);
    }
}