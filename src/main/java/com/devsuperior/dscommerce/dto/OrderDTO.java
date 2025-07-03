package com.devsuperior.dscommerce.dto;

import java.time.Instant;
import java.util.Set;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;

import jakarta.persistence.Column;

public class OrderDTO {

	private Long id;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant moment;
	private OrderStatus status;

	public OrderDTO() {
	}

	public OrderDTO(Long id, Instant moment, OrderStatus status, Set<OrderItem> items) {
		this.id = id;
		this.moment = moment;
		this.status = status;
	}
	
	public OrderDTO(Order order) {
		id = order.getId();
		moment = order.getMoment();
		status = order.getStatus();
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

}
