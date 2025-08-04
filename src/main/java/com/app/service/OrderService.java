package com.app.service;


import com.app.dto.OrderResponse;
import com.app.mapper.MapperClass;
import com.app.model.*;
import com.app.repo.OrderRepo;
import com.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartItemService cartItemService;

    private final UserRepo userRepo;

    private final OrderRepo orderRepo;

    public OrderResponse createOrder(String userId) {

        List<CartItem> allCartItems = cartItemService.getAllCartItems(userId);

        if(allCartItems.isEmpty()){
            throw new RuntimeException("cart is  empty");
        }

        Optional<User> userOptional = userRepo.findById(Long.valueOf(userId));

        if(userOptional.isEmpty()){
            throw new RuntimeException("user is not exist");
        }

        BigDecimal totalPrice = allCartItems.stream().
                map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();

        order.setUser(userOptional.get());
        order.setTotalAmount(totalPrice);
        order.setCreatedAt(order.getCreatedAt());
        order.setOrderStatus(OrderStatus.CONFIRMED);

        List<OrderItems>  orderItems = allCartItems.stream().map(item->
                new OrderItems(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                )).toList();

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(order);

        cartItemService.clearCart(userId);

        return MapperClass.mapToOrderResponse(savedOrder);
    }


}
