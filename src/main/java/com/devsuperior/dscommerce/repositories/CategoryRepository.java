package com.devsuperior.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT obj FROM Category obj")
	Page<Category> searchAll(Pageable pageable);
}
