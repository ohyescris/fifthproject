package com.devsuperior.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT obj "
			+ "FROM Order obj "
			+ "JOIN FETCH obj.client "
			+ "WHERE obj.id = :orderId "
			+ "AND obj.client.email = :email")
	Order searchByIdAndClientEmail(Long orderId, String email);
	
	@Query("SELECT obj "
			+ "FROM Order obj "
			+ "JOIN FETCH obj.client "
			+ "WHERE obj.id = :orderId ")
	Order searchById(Long orderId);
}
