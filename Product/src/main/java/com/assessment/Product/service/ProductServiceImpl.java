package com.assessment.Product.service;

import com.assessment.Product.entity.Product;
import com.assessment.Product.exception.ProductNotFoundException;
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
        log.info("Inside service get product details {}",id);
        Product product=  productRepo.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found with id"+id,"PRODUCT_NOT_FOUND",404));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long id, long quantity) {
        Product product = productRepo.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found with id"+id,"PRODUCT_NOT_FOUND",404));
        log.info("Product id {} and Quantity {}: ",id,product.getQuantity());
        if(product.getQuantity()<quantity){
            throw new ProductNotFoundException("Product Quantity is Insufficient "+id,"INSUFFICIENT",500);
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepo.save(product);
        log.info("Product updated successfully");
    }
}
