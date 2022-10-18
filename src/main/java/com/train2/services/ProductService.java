package com.train2.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train2.model.entity.Product;
import com.train2.model.repository.ProductRepo;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public Product findOne(Long id){
        Optional<Product> temp = productRepo.findById(id);
        if(temp.isPresent()) {
        return productRepo.findById(id).get();
        }
        return null;
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }
}
