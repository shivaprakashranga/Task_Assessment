package com.assessment.Product.exception;

import com.assessment.Product.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductResposeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> displayError(ProductNotFoundException exception){
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .errorStatus(exception.getErrorStatus())
                .errorCode(exception.getErrorCode())
                .build();
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
