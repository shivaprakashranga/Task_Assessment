package com.assessment.orderService.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    private String errorCode;
    private String errorStatus;

    public CustomException(String message, String errorStatus, String errorCode){
        this.errorCode=errorCode;
        this.errorStatus=errorStatus;
    }

}
