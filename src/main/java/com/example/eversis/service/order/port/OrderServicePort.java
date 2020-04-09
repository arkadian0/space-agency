package com.example.eversis.service.order.port;

import com.example.eversis.controller.transfer.OrderProductDto;

import java.util.List;

public interface OrderServicePort {
    void createOrders(List<OrderProductDto> productDtos, String userName);
}
