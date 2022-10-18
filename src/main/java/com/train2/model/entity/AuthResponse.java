package com.train2.model.entity;

public class AuthResponse {
    private final String jwt;
  
    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
    
}
