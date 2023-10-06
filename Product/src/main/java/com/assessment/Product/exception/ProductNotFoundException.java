package com.assessment.Product.exception;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException{

    private String errorStatus;
    private int errorCode;
    public ProductNotFoundException(String message, String errorStatus,int errorCode){
        super(message);
        this.errorStatus=errorStatus;
        this.errorCode=errorCode;
    }
}
