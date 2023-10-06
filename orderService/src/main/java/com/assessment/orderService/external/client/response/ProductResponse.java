package com.assessment.orderService.external.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponse {
    private String productName;
    private long id;
    private long quantity;
    private long price;
}
