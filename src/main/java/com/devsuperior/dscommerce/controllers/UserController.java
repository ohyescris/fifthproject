package com.devsuperior.dscommerce.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping("/me")
    public String getLoggedUser(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("username");
        return "Usuário atual: " + username;
    }
    
    //  + "\nSua autoridade é: " + jwt.getClaimAsString("authorities")
}