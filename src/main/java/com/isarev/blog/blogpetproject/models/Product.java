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
    private int quantity_per_unit;
    private BigDecimal unit_price;

    public Product(String product_name, String supplier, int quantity_per_unit, BigDecimal unit_price) {
        this.product_name = product_name;
        this.supplier = supplier;
        this.quantity_per_unit = quantity_per_unit;
        this.unit_price = unit_price;
    }
}
