package com.danielkim.laboratory.springbootweb.api.controllers;

import com.danielkim.laboratory.springbootweb.api.domain.user.UserAuthenticationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserAuthenticationData authenticationData){
        var token = new UsernamePasswordAuthenticationToken(authenticationData.username(), authenticationData.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}