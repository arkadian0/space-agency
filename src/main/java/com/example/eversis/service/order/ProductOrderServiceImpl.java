package com.example.eversis.service.order;

import com.example.eversis.controller.transfer.OrderProductDto;
import com.example.eversis.dao.OrderRepository;
import com.example.eversis.dao.ProductRepository;
import com.example.eversis.dao.UserRepository;
import com.example.eversis.exceptions.ProductNotFoundExceptions;
import com.example.eversis.exceptions.UserNotFoundException;
import com.example.eversis.model.CustomerOrder;
import com.example.eversis.model.OrderItem;
import com.example.eversis.model.Product;
import com.example.eversis.model.User;
import com.example.eversis.service.order.port.OrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements OrderServicePort {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void createOrders(List<OrderProductDto> orderProductDtos, String userName) {
        CustomerOrder order = createOrderForUser(userName);
        List<OrderItem> orderItems = createOrderItems(orderProductDtos, order);
        orderRepository.saveAll(orderItems);

    }

    private List<OrderItem> createOrderItems(List<OrderProductDto> orderProductDtos, CustomerOrder order) {
        return orderProductDtos.stream().map(dto -> {
            Product product = findProduct(dto.getProductId());
            return new OrderItem(order, product, dto.getQuantity());
        }).collect(Collectors.toList());
    }

    private Product findProduct(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundExceptions(String.format("Product with id : %s not found", productId)));
    }

    private CustomerOrder createOrderForUser(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isPresent()) {
            return new CustomerOrder(user.get());
        } else
            throw new UserNotFoundException(String.format("User with username : %s not found", userName));
    }
}
