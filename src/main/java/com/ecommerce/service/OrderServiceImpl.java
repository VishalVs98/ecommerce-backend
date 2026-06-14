package com.ecommerce.service;

import com.ecommerce.dto.OrderItemRequestDTO;
import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.entity.*;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(
            OrderRequestDTO request) {

        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        CustomerOrder order = new CustomerOrder();
        order.setUser(user);
        
        order.setStatus(
                OrderStatus.CONFIRMED);

        order.setOrderDate(
                LocalDateTime.now());

        BigDecimal totalAmount =
                BigDecimal.ZERO;

        List<OrderItem> orderItems =
                new ArrayList<>();

        for (OrderItemRequestDTO itemDto
                : request.getItems()) {

            Product product =
                    productRepository
                            .findById(
                                    itemDto.getProductId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Product not found"));

            if (product.getStock()
                    < itemDto.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient stock for "
                                + product.getName());
            }

            product.setStock(
                    product.getStock()
                            - itemDto.getQuantity());

            productRepository.save(product);

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setOrder(order);

            orderItem.setProduct(product);

            orderItem.setQuantity(
                    itemDto.getQuantity());

            orderItem.setPrice(
                    product.getPrice());

            orderItems.add(orderItem);

            totalAmount =
                    totalAmount.add(
                           product.getPrice()
                                    .multiply(
                                            BigDecimal.valueOf(
                                                    itemDto.getQuantity())));
        }

        order.setTotalAmount(
                totalAmount);

        order.setOrderItems(
                orderItems);

        CustomerOrder savedOrder =
                orderRepository.save(order);

        return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus().name());
    }
}
