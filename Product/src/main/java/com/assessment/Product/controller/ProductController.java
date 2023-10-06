package com.assessment.Product.controller;

import com.assessment.Product.model.ProductRequest;
import com.assessment.Product.model.ProductResponse;
import com.assessment.Product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping
   public ResponseEntity<Long> saveProductDetails(@RequestBody ProductRequest productRequest){
        log.info("Inside controller saveProduct");
        long prodID  = productService.saveProductDetails(productRequest);
        return new ResponseEntity<>(prodID,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductDetailsById(@PathVariable long id){
        ProductResponse productResponse = productService.getProductDetailsById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long id, @RequestParam long quantity){
        log.info("inside controller {}: {}:",id,quantity);
        productService.reduceQuantity(id, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
        //return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
