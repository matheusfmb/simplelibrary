package com.simplelibrary.dto;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Autor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDTO {
	
	private Integer idAutor;

	@NotNull(message="Nome não pode ser nulo")
	@Size(max=50)
	private String nomeAutor;
	
	@NotNull(message="Nacionalidade não pode ser nulo")
	@Size(max=30)
	private String nacionalidade;
	
	@NotNull(message="imgUrl não pode ser nulo")
	private String imgUrl;
	
	@NotNull(message="Descrição do autor não pode ser nulo")
	private String autorDescricao;
	
	public AutorDTO() {
		
	}
	
	public AutorDTO(Autor entity) {
		BeanUtils.copyProperties(entity, this);
	}


}
