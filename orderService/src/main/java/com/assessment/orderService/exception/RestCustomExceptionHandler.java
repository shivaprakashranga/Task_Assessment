package com.assessment.orderService.exception;

import com.assessment.orderService.entity.Order;
import com.assessment.orderService.external.client.response.ErrorResponse;
import com.assessment.orderService.model.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customeExpectionHanlderResponse(CustomException exception){
        ErrorResponse response = ErrorResponse
                .builder()
                .errorCode(exception.getErrorCode())
                .errorStatus(exception.getErrorStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
