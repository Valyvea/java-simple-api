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

import com.train2.model.entity.UserAccount;
import com.train2.services.UserAccountService;

@RestController
@RequestMapping("/users")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping
    public UserAccount create(@RequestBody UserAccount userAccount) {
        return userAccountService.save(userAccount);
    }

    @GetMapping
    public Iterable<UserAccount> findAll(){
        return userAccountService.findAll();
    }

    @GetMapping("/{username}")
    public UserAccount findByUsername(@PathVariable("username") String username){
        return userAccountService.findByUsername(username);
    }

    @PutMapping
    public UserAccount update(@RequestBody UserAccount userAccount){
        return userAccountService.save(userAccount);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") String id){
        userAccountService.removeOne(id);
    }
}
