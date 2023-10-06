package com.assessment.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long id;
    private Instant orderDate;
    private String orderStatus;
    private long totalAmount;
}
