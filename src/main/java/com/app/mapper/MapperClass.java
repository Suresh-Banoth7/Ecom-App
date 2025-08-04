package com.app.mapper;

import com.app.dto.*;
import com.app.model.Address;
import com.app.model.Order;
import com.app.model.Product;
import com.app.model.User;

import java.math.BigDecimal;

public class MapperClass {


    public static  UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        Address address = user.getAddress();

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());

        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setName(user.getName());
        userResponse.setPhone(user.getPhone());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(String.valueOf(user.getRole()));
        userResponse.setAddress(addressDTO);

        return userResponse;
    }

    public static User mapToUser(UserRequest userRequest) {

          AddressDTO addressDTO = userRequest.getAddress();

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());

          User user = new User();
          user.setAddress(address);
          user.setName(userRequest.getName());
          user.setEmail(userRequest.getEmail());
          user.setPhone(userRequest.getPhone());

          return user;
    }

    //entity to response
    public static ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setDescription(product.getDescription());
        productResponse.setActive(product.getActive());
        productResponse.setImage(product.getImage());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }

    // Request to entity
    public static Product mapToProduct(ProductRequest productRequest) {
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setCategory(productRequest.getCategory());
        return product;
    }

    public static OrderResponse mapToOrderResponse(Order order) {

       return new OrderResponse(
               null,
               order.getUser(),
               order.getTotalAmount(),
               order.getOrderStatus(),
               order.getOrderItems().stream().map(item ->
                       new OrderItemsDTO(
                               item.getId(),
                               item.getProduct(),
                               item.getQuantity(),
                               item.getPrice(),
                               item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                       )).toList(),
               order.getCreatedAt()
       );

    }

}
