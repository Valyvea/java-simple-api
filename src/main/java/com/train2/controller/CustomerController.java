package com.train2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train2.model.entity.Customer;
import com.train2.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public Iterable<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findOne(@PathVariable("id") Long id){
        return customerService.findOne(id);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        customerService.removeOne(id);
    }
}
