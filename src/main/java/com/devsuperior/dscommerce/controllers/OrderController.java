package com.devsuperior.dscommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
    	String username = jwt.getClaimAsString("username");
    	List<String> authorities = jwt.getClaimAsStringList("authorities");
    	if (authorities.contains("ROLE_ADMIN")) {
    		OrderDTO dto = service.findById(id);
    		// poderia ser melhorado o mecanismo de busca aqui, mas não é o objetivo atual
    		return ResponseEntity.ok(dto);
    	}
    	else {
    		OrderDTO dto = service.findByIdAndClientEmail(id, username);
    		return ResponseEntity.ok(dto);
    	}
    }
}
