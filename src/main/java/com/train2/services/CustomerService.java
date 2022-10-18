package com.train2.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train2.model.entity.Customer;
import com.train2.model.repository.CustomerRepo;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer save(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer findOne(Long id){
        Optional<Customer> temp = customerRepo.findById(id);
        if(temp.isPresent()) {
        return customerRepo.findById(id).get();
        }
        return null;
    }

    public Iterable<Customer> findAll(){
        return customerRepo.findAll();
    }

    public void removeOne(Long id){
        customerRepo.deleteById(id);
    }

    public List<Customer> findByName(String name) {
        return customerRepo.findByNameContains(name);
    }
}
