package com.app.repo;

import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);


      List<CartItem>  findByUser(User user);

    void deleteByUser(User user);
}
