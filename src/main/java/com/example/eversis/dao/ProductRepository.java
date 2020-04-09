package com.example.eversis.dao;

import com.example.eversis.controller.transfer.SearchProductDto;
import com.example.eversis.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p where p.mission.name = :#{#searchProductDto.missionName} and p.mission.imageryType =:#{#searchProductDto.imageryType} and p.acquisitionDate < :#{#searchProductDto.acquisitionDateFrom}")
    List<Product> findProductsByDateLowerThanAcquisitionDate(SearchProductDto searchProductDto);

    @Query("SELECT p FROM Product p where p.mission.name = :#{#searchProductDto.missionName} and p.mission.imageryType =:#{#searchProductDto.imageryType} and p.acquisitionDate > :#{#searchProductDto.acquisitionDateFrom}")
    List<Product> findProductsByDateGraterThanAcquisitionDate(SearchProductDto searchProductDto);

    @Query("SELECT p FROM Product p where p.mission.name = :#{#searchProductDto.missionName} and p.mission.imageryType =:#{#searchProductDto.imageryType} and p.acquisitionDate >= :#{#searchProductDto.acquisitionDateFrom} and p.acquisitionDate <= :#{#searchProductDto.acquisitionDateTo}")
    List<Product> findProductsByDateBetweenAcquisitionDate(SearchProductDto searchProductDto);

}
