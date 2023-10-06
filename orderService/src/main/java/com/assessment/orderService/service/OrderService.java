package com.assessment.orderService.service;

import com.assessment.orderService.model.OrderRequest;
import com.assessment.orderService.model.OrderResponse;

public interface OrderService {
   public long placeOrder(OrderRequest orderRequest);
   public OrderResponse getOrderDetails(long id);
}
