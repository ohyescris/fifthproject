package com.devsuperior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findByIdAndClientEmail(Long id, String email) {
    	try {
	    	Order order = repository.searchByIdAndClientEmail(id, email);
	    	return new OrderDTO(order);
		}
    	catch (NullPointerException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }
    
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
    	try {
	    	Order order = repository.searchById(id);
	    	return new OrderDTO(order);
		}
    	catch (NullPointerException | EntityNotFoundException e ) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }
}
