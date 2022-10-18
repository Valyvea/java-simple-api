package com.train2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.train2.model.entity.AuthRequest;
import com.train2.model.entity.AuthResponse;
import com.train2.model.entity.UserAccount;
import com.train2.utility.JwtTokenUtil;

@RestController
public class AuthController {
    @Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;
     
    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );
             
            UserAccount user = (UserAccount) authentication.getPrincipal();
            String jwt = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(jwt);
             
            return ResponseEntity.ok().body(response);
             
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
}}
