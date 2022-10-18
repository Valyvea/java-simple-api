package com.train2.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.train2.model.entity.UserAccount;

public interface UserAccountRepo extends CrudRepository<UserAccount, String>{
    UserAccount findByUsername(String username);
}
