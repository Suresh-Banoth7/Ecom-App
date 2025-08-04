package com.app.service;


import com.app.dto.CartItemRequest;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;
import com.app.repo.CartItemRepo;
import com.app.repo.ProductRepo;
import com.app.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {

    private final CartItemRepo cartItemRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    public void addToCart(String id, CartItemRequest cartItemRequest) {

         Optional<User> optionalUser = userRepo.findById(Long.valueOf(id));

         if(optionalUser.isEmpty()){
             throw new RuntimeException("user not found");
         }

        Optional<Product> optionalProduct = productRepo.findById(cartItemRequest.getProductId());
         if(optionalProduct.isEmpty()){
             throw new RuntimeException("product not found");
         }


         User user = optionalUser.get();
         Product product = optionalProduct.get();

         if(product.getQuantity() < cartItemRequest.getQuantity()){
             throw new RuntimeException("product not enough");
         }

         CartItem existinCart = cartItemRepo.findByUserAndProduct(user , product);

         if(existinCart != null){
             existinCart.setQuantity(existinCart.getQuantity() + cartItemRequest.getQuantity());
             existinCart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
             cartItemRepo.save(existinCart);
         }else{
             CartItem cartItem = new CartItem();
             cartItem.setUser(user);
             cartItem.setProduct(product);
             cartItem.setQuantity(cartItemRequest.getQuantity());
             cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
             cartItemRepo.save(cartItem);
         }


    }

    public void deleteFromCart(String id, String productId) {

        Optional<User> optionalUser = userRepo.findById(Long.valueOf(id));

        Optional<Product> optionalProduct = productRepo.findById(Long.valueOf(productId));

        if(optionalProduct.isEmpty() && optionalUser.isEmpty()){
            throw new RuntimeException("product or user not found");
        }

        cartItemRepo.deleteByUserAndProduct(optionalUser.get(), optionalProduct.get());
    }

    public List<CartItem> getAllCartItems(String id) {

              return userRepo.findById(Long.valueOf(id)).map(cartItemRepo::findByUser).orElseGet(List::of);
    }

    public void clearCart(String userId) {

       userRepo.findById(Long.valueOf(userId)).ifPresent(user -> {

           cartItemRepo.deleteByUser(user);
       });

    }
}
