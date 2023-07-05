package com.simplelibrary.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Categoria;
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
	
	private List<AutorMinDTO> autor;
	private List<Categoria> categoria;
	
	public LivroMinDTO() {
		
	}
	
	public LivroMinDTO(Livro entity) {
		BeanUtils.copyProperties(entity, this);
	}

}
