package com.vincent.productservice.service;

import com.vincent.productservice.model.ProductRequest;
import com.vincent.productservice.model.ProductResponse;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    void reduceQuantity(Long productId, Long quantity);
}
