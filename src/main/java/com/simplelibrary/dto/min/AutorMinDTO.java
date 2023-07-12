package com.simplelibrary.dto.min;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Autor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorMinDTO {
	
	private Integer idAutor;
	private String nomeAutor;
	
	public AutorMinDTO() {
		
	}
	
	public AutorMinDTO(Autor entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
