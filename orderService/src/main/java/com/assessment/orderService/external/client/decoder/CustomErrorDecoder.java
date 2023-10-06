package com.assessment.orderService.external.client.decoder;

import com.assessment.orderService.exception.CustomException;
import com.assessment.orderService.external.client.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("url {}",response.request().url());
        log.info("header {}",response.request().headers());
        try {
          ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
          return new CustomException(errorResponse.getErrorMessage(),errorResponse.getErrorStatus(),errorResponse.getErrorCode());
        }
        catch (IOException e){
            throw  new CustomException("Internal Server Error",
                    "INTERNAL_SERVER_ERROR",
                    "500");
        }
    }
}
