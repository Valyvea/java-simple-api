package com.train2.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.train2.model.entity.UserAccount;
import com.train2.model.repository.UserAccountRepo;

@Service
@Transactional
public class UserAccountService {
    private PasswordEncoder passwordEncoder;
    public UserAccountService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserAccountRepo userAccountRepo;
    public UserAccount save(UserAccount userAccount){
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        return userAccountRepo.save(userAccount);
    }

    public UserAccount findOne(String id){
        Optional<UserAccount> temp = userAccountRepo.findById(id);
        if(temp.isPresent()) {
        return userAccountRepo.findById(id).get();
        }
        return null;
    }

    public Iterable<UserAccount> findAll(){
        return userAccountRepo.findAll();
    }

    public void removeOne(String id){
        userAccountRepo.deleteById(id);
    }

    public UserAccount findByUsername(String username) {
        return userAccountRepo.findByUsername(username);
    }
}
