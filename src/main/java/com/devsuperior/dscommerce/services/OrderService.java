package com.devsuperior.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dscommerce.dto.OrderMaxDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ProductRepository productRepository;

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
    
    @Transactional
    public OrderMaxDTO insert(OrderMaxDTO dto) {
    	Order entity = new Order();
    	
    	entity.setMoment(Instant.now());
    	entity.setStatus(OrderStatus.valueOf("WAITING_PAYMENT"));
    	
    	for (OrderItemDTO itemDTO : dto.getItems()) {
    		OrderItem tmpItem = new OrderItem();
    		Product tmpProduct = productRepository.findById(itemDTO.getProductId()).orElseThrow(
                    () -> new ResourceNotFoundException("Recurso não encontrado"));
    		tmpItem.setProduct(tmpProduct);
    		tmpItem.setQuantity(itemDTO.getQuantity());
    		tmpItem.setOrder(entity);
    		
    		entity.getItems().add(tmpItem);
    	}
    	
        entity = repository.save(entity);
        return new OrderMaxDTO(entity);
    }
}
