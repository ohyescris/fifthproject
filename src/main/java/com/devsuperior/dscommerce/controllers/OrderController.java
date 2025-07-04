package com.devsuperior.dscommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderMaxDTO;
import com.devsuperior.dscommerce.services.OrderService;

import jakarta.validation.Valid;

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
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<OrderMaxDTO> insert(@Valid @RequestBody OrderMaxDTO dto, @AuthenticationPrincipal Jwt jwt) {
    	String username = jwt.getClaimAsString("username");
        dto = service.insert(dto, username);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
