package com.app.controller;


import com.app.dto.CartItemRequest;
import com.app.model.CartItem;
import com.app.service.CartItemService;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {


    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<Void> addToCart(@RequestHeader("X-user-ID") String Id ,
                                          @RequestBody CartItemRequest  cartItemRequest) {


        cartItemService.addToCart(Id , cartItemRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteFromCart(@RequestHeader("X-user-ID") String Id ,
                                               @PathVariable("productId") String productId) {

        cartItemService.deleteFromCart(Id , productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>>  getAllCartItems(@RequestHeader("X-user-ID") String Id ){

      List<CartItem> items = cartItemService.getAllCartItems(Id);
        return ResponseEntity.ok(items);
    }
}
