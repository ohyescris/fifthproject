package com.devsuperior.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
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
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthService authService;

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
    	
    	Order order = repository.findById(id).orElseThrow(
    			() -> new ResourceNotFoundException("Recurso não encontrado"));
    	authService.validateSelfOrAdmin(order.getClient().getId());
    	return new OrderDTO(order);
		
    }
    
    @Transactional
    public OrderDTO insert(OrderDTO dto) {
    	
    	Order order = new Order();
    	
    	order.setMoment(Instant.now());
    	order.setStatus(OrderStatus.WAITING_PAYMENT);
    	
    	User user = userService.authenticated();
    	order.setClient(user);
    	
    	
    	for (OrderItemDTO itemDto : dto.getItems()) {
    		Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(
                    () -> new ResourceNotFoundException("Recurso não encontrado"));
    		OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
    		order.getItems().add(item);
    	}
    	
    	repository.save(order);
    	orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }
}
