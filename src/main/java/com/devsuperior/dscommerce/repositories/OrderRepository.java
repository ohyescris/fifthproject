package com.devsuperior.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT obj "
			+ "FROM Order obj "
			+ "WHERE obj.client.email = :email")
	Page<Order> searchByEmail(Pageable pageable);
}
