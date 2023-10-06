package com.assessment.orderService.service;

import com.assessment.orderService.entity.Order;
import com.assessment.orderService.exception.CustomException;
import com.assessment.orderService.external.client.ProductService;
import com.assessment.orderService.external.client.response.ProductResponse;
import com.assessment.orderService.model.OrderRequest;
import com.assessment.orderService.model.OrderResponse;
import com.assessment.orderService.repository.OrderRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;
    //Order Entity -> Save the data with Status Order Created
    //Product Service - Block Products (Reduce the Quantity)
    //Payment Service -> Payments -> Success-> COMPLETE, Else
    //CANCELLED

    public long placeOrder(OrderRequest orderRequest){
        productService.getProductDetailsById(orderRequest.getProductId());

        Order order = Order
                .builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .totalAmount(orderRequest.getTotalAmount())
                .build();
        orderRepo.save(order);

        //Reducing Quantity details in Product service
        log.info("Entering Reduce Quantity");
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        order.setOrderStatus("PLACED");
        orderRepo.save(order);
        log.info("updated order details");
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long id) {
        log.info("Order details for the id {}",id);
        Order order= orderRepo.findById(id).orElseThrow(()->new CustomException("Order not found","ORDER_NOT_FOUND","404"));
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(order,orderResponse);
        return orderResponse;
    }

}
