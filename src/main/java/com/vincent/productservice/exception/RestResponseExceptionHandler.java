package com.vincent.productservice.exception;

import com.vincent.productservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> HandleProductServiceException(ProductServiceCustomException exception){
        return  new ResponseEntity<>(new ErrorResponse().builder()
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
