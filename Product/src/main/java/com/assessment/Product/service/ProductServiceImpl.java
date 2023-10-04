package com.assessment.Product.service;

import com.assessment.Product.entity.Product;
import com.assessment.Product.model.ProductRequest;
import com.assessment.Product.model.ProductResponse;
import com.assessment.Product.repository.ProductRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;
    @Override
    public Long saveProductDetails(ProductRequest productRequest) {
        log.info("Inside service saveProduct {}:",productRequest.getName());
        Product product = Product
                .builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepo.save(product);
        return product.getId();
    }

    @Override
    public ProductResponse getProductDetailsById(long id) {
        log.info("Inside service get product details {}");

       Product product=  productRepo.findById(id).orElseThrow(()->new RuntimeException("Product not found with id"+id));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product,productResponse);
        return productResponse;
    }
}
