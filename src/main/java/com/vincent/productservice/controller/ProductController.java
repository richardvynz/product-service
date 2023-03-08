package com.vincent.productservice.controller;

import com.vincent.productservice.model.ProductRequest;
import com.vincent.productservice.model.ProductResponse;
import com.vincent.productservice.repository.ProductRepository;
import com.vincent.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long>addProduct(@RequestBody ProductRequest productRequest){
        Long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse>getProductById(@PathVariable("id") Long productId){
       ProductResponse productResponse = productService.getProductById(productId);
       return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
        public ResponseEntity<Void> reduceQuantity(
                @PathVariable("id") Long productId,
                @RequestParam Long quantity
        ){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
        }

}
