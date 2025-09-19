package com.ecommerce.orderservice.exception;

import com.ecommerce.commons.dto.ErrorResponse;
import com.ecommerce.commons.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class OrderServiceExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "The requested order does not exist"
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ProductQuantityNotEnoughException.class)
    public ResponseEntity<ErrorResponse> handleProductQuantityNotEnough(ProductQuantityNotEnoughException ex) {
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "Not enough product quantity"
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
