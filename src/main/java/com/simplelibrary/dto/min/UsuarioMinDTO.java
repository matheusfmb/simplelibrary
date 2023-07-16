package com.simplelibrary.dto.min;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioMinDTO {
	
	private Integer id;
	private String nome;
	
	public UsuarioMinDTO() {
		
	}
	
	public UsuarioMinDTO(Usuario entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
