package com.app.service;


import com.app.dto.ProductRequest;
import com.app.dto.ProductResponse;
import com.app.mapper.MapperClass;
import com.app.model.Product;
import com.app.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public ProductResponse createProduct( ProductRequest productRequest) {

        Product product = MapperClass.mapToProduct(productRequest);

          Product savedProduct =  productRepo.save(product);

         return MapperClass.mapToProductResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {

   return productRepo.findAll().stream().map( MapperClass::mapToProductResponse).toList();
    }

    public ProductResponse productService(String id, ProductRequest productRequest) {

        Optional<Product> existingProduct = productRepo.findById(Long.valueOf(id));

        if(existingProduct.isEmpty()){
            throw new RuntimeException("user not found");
        }

        Product product = existingProduct.get();

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setQuantity(productRequest.getQuantity());
        product.setImage(productRequest.getImage());

        Product savedProduct = productRepo.save(product);
        return MapperClass.mapToProductResponse(savedProduct);
    }



    public void deleteProduct(String id) {

        Optional<Product> isProduct = productRepo.findById(Long.valueOf(id));

        if(isProduct.isEmpty()){
            throw new RuntimeException("product not found");
        }

        productRepo.deleteById(Long.valueOf(id));

        Product product = new Product();
       product.setActive(false);

    }
}
