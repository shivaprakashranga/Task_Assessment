package com.assessment.orderService.external.client;

import com.assessment.orderService.external.client.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long id, @RequestParam long quantity);

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductDetailsById(@PathVariable long id);
}
