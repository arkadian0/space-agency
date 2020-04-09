package com.example.eversis.service.product;

import com.example.eversis.controller.transfer.ProductDto;
import com.example.eversis.dao.MissionRepository;
import com.example.eversis.exceptions.MissionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplUnitTest {

    @Mock
    private MissionRepository missionRepository;

    @InjectMocks
    private ProductImageServiceImpl sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void throw_exception_when_mission_not_found() {
        //given
        final String missionId = "notfound";
        ProductDto productDto = ProductDto.builder().build();
        when(missionRepository.findById(any())).thenReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> sut.save(List.of(productDto),missionId));

        // then
        assertThat(thrown).isInstanceOf(MissionNotFoundException.class)
                .hasMessageContaining(String.format("Mission with id : %s not found", missionId));

    }

}