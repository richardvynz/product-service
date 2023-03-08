package com.vincent.productservice.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException{
    private String errorCode;
    public ProductServiceCustomException(String errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }
}
