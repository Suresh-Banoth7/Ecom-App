package com.app.dto;

import com.app.model.OrderItems;
import com.app.model.OrderStatus;
import com.app.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;


    private User user;

    private BigDecimal totalAmount;

    private OrderStatus orderStatus;

    private List<OrderItemsDTO> orderItems;


    private LocalDateTime createdAt;



}
