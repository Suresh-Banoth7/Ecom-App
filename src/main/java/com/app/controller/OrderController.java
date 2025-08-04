package com.app.controller;


import com.app.dto.OrderResponse;
import com.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

       private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponse>  createOrder(@RequestHeader("X-user-ID") String userId){

       OrderResponse order = orderService.createOrder(userId);
        return ResponseEntity.ok(order);

    }

}
