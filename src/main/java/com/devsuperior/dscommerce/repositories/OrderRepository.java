package com.devsuperior.dscommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT obj "
			+ "FROM Order obj "
			+ "JOIN FETCH obj.client "
			+ "WHERE obj.id = :orderId "
			+ "AND obj.client.email = :email")
	Order findByIdAndClientEmail(Long orderId, String email);
}
