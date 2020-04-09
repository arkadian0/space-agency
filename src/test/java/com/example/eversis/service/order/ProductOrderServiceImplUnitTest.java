package com.example.eversis.service.order;

import com.example.eversis.controller.transfer.OrderProductDto;
import com.example.eversis.dao.OrderRepository;
import com.example.eversis.dao.ProductRepository;
import com.example.eversis.dao.UserRepository;
import com.example.eversis.exceptions.ProductNotFoundExceptions;
import com.example.eversis.exceptions.UserNotFoundException;
import com.example.eversis.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ProductOrderServiceImplUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    private ProductOrderServiceImpl sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void throw_exception_when_user_not_found() {
        //given
        final String username = "fake";
        when(userRepository.findByUserName(any())).thenReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> sut.createOrders(Collections.emptyList(),username));

        // then
        assertThat(thrown).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(String.format("User with username : %s not found", username));

    }

    @Test
    void throw_exception_when_product_not_found() {
        //given
        final Integer fakeId = -23213;
        final String username = "fake";

        OrderProductDto orderProductDto = OrderProductDto.builder().productId(fakeId).quantity(4).build();
        when(userRepository.findByUserName(any())).thenReturn(Optional.of(new User()));
        when(productRepository.findById(fakeId)).thenReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> sut.createOrders(List.of(orderProductDto),username));

        // then
        assertThat(thrown).isInstanceOf(ProductNotFoundExceptions.class)
                .hasMessageContaining(String.format("Product with id : %s not found", fakeId));

    }
}