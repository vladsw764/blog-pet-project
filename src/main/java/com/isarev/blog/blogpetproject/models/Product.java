package com.isarev.blog.blogpetproject.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long product_id;
    private String product_name;
    private String supplier;
    private String description;
    private BigDecimal unit_price;

    public Product(String product_name, String supplier, String description, BigDecimal unit_price) {
        this.product_name = product_name;
        this.supplier = supplier;
        this.description = description;
        this.unit_price = unit_price;
    }
}
