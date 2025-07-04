package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.OrderItem;

public class OrderItemDTO {

	private Long productId;
	private Integer quantity;

	public OrderItemDTO() {
	}

	public OrderItemDTO(Long productId, Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public OrderItemDTO(OrderItem entity) {
		productId = entity.getProduct().getId();
		quantity = entity.getQuantity();
	}

	public Long getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
