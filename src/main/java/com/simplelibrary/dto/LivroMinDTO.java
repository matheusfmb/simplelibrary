package com.simplelibrary.dto;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Livro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroMinDTO {
	
	private Integer idLivro;
	private String nomeLivro;
	private String shortDescription;
	private String imgUrl;
	
	public LivroMinDTO() {
		
	}
	
	public LivroMinDTO(Livro entity) {
		BeanUtils.copyProperties(entity, this);
	}

}
