package com.vincent.productservice.service.serviceImpl;

import com.vincent.productservice.entity.Product;
import com.vincent.productservice.exception.ProductServiceCustomException;
import com.vincent.productservice.model.ProductRequest;
import com.vincent.productservice.model.ProductResponse;
import com.vincent.productservice.repository.ProductRepository;
import com.vincent.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("Adding product in product serviceImpl");
        Product product =
                Product.builder()
                        .productName(productRequest.getName())
                        .quantity(productRequest.getQuantity())
                        .price(productRequest.getPrice())
                        .build();
        productRepository.save(product);

        log.info("product created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Get the info for product with productId,{}: ",productId);
        Product product = productRepository.findById(productId).orElseThrow(
                ()->new ProductServiceCustomException("Product with that id is not found!","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse =
                new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("Reduce quantity {}, for Id {}",quantity ,productId);

        Product product =
                productRepository.findById(productId).orElseThrow(
                        ()-> new ProductServiceCustomException("Product with given Id not found",
                                "PRODUCT_NOT_FOUND")
                );

        if(product.getQuantity() < quantity){
            throw new ProductServiceCustomException("Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity()-quantity);

        productRepository.save(product);

        log.info("Product Quantity Updated Successfully!");
    }
}
