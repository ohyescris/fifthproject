package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

	private Long id;

	@Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
