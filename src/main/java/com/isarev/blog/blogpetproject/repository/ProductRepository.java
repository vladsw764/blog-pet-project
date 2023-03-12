package com.isarev.blog.blogpetproject.repository;

import com.isarev.blog.blogpetproject.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
