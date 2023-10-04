package com.assessment.Product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "PRODUCT_SERVICE")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    @Id
    private long id;

    @Column(name = "ProductName")
    private String productName;

    @Column(name="PRICE")
    private long price;

    @Column(name = "Quantity")
    private long quantity;

}
