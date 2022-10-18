package com.train2.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.train2.model.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findByNameContains(String name);
}
