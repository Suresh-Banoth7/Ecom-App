package com.app.controller;


import com.app.dto.ProductRequest;
import com.app.dto.ProductResponse;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody  ProductRequest productRequest) {

        ProductResponse product = productService.createProduct(productRequest);

        Map<String, Object> response = new HashMap<>();

        response.put("product", product);
        response.put("status", HttpStatus.OK);
        response.put("message", "Product created successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

           List<ProductResponse> productResponses =  productService.getAllProducts();

           return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable String id,@RequestBody  ProductRequest productRequest) {

        ProductResponse updatedProduct = productService.productService(id , productRequest);

        Map<String, Object> response = new HashMap<>();

        response.put("product", updatedProduct);
        response.put("status", HttpStatus.OK);
        response.put("message", "Product updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {

        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
