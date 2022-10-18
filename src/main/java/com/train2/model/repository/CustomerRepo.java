package com.train2.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.train2.model.entity.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
    List<Customer> findByNameContains(String name);
}
