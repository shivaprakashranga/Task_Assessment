package com.assessment.orderService.controller;

import com.assessment.orderService.model.OrderRequest;
import com.assessment.orderService.model.OrderResponse;
import com.assessment.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public long placeOrder(@RequestBody OrderRequest orderRequest){
       long id =  orderService.placeOrder(orderRequest);
        return id;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long id){
        OrderResponse orderResponse = orderService.getOrderDetails(id);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
