package com.danielkim.laboratory.springbootweb.api.controllers;

import com.danielkim.laboratory.springbootweb.api.infrastructure.security.TokenJWTData;
import com.danielkim.laboratory.springbootweb.api.infrastructure.security.TokenService;
import com.danielkim.laboratory.springbootweb.api.domain.user.User;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTData> login(@RequestBody @Valid UserAuthenticationData authenticationData){
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.username(), authenticationData.password());

        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
}