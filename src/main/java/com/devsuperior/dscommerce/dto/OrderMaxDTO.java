package com.devsuperior.dscommerce.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderMaxDTO {

	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", timezone = "UTC")
	private Instant moment;
	
	private OrderStatus status;

	private Set<OrderItemDTO> items = new HashSet<>();

	public OrderMaxDTO() {
	}

	public OrderMaxDTO(Long id, Set<OrderItemDTO> items) {
		this.id = id;
		this.moment = Instant.now();
		status = OrderStatus.valueOf("WAITING_PAYMENT");

		for (OrderItemDTO dto : items) {
			this.items.add(dto);
		}
	}
	
	public OrderMaxDTO(Order entity) {
		id = entity.getId();
		moment = entity.getMoment();
		status = entity.getStatus();
		
		for (OrderItem entityItem : entity.getItems()) {
			items.add(new OrderItemDTO(entityItem));
		}
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

	public Set<OrderItemDTO> getItems() {
		return items;
	}

}
