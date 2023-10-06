package com.assessment.Product.service;

import com.assessment.Product.model.ProductRequest;
import com.assessment.Product.model.ProductResponse;

public interface ProductService {
    Long saveProductDetails(ProductRequest productRequest);

    ProductResponse getProductDetailsById(long id);

    void reduceQuantity(long id, long quantity);
}
