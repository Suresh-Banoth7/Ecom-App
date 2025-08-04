package com.app.dto;

import com.app.model.Order;
import com.app.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemsDTO {

    private Long id;
    private Product product;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

   // private Order order;


}
