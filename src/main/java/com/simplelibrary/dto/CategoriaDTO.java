package com.simplelibrary.dto;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Categoria;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {
	
	private Integer idCategoria;
	
	@NotNull(message="nome da categoria não pode ser Nulo")
	private String nomeCategoria;
	
	public CategoriaDTO() {
		
	}
	public CategoriaDTO(Categoria entity) {
		BeanUtils.copyProperties(entity, this);
	}
	
	
}
