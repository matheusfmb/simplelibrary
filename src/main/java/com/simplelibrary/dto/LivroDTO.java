package com.simplelibrary.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.dto.min.AutorMinDTO;
import com.simplelibrary.entities.Categoria;
import com.simplelibrary.entities.Livro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {
	
	private Integer idLivro;
	private String nomeLivro;
	private Integer anoLancamento;
	private String shortDescription;
	private String longDescription;
	private Integer qtdExemplares;
	private String imgUrl;
	
	private List<AutorMinDTO> autor;
	private List<Categoria> categoria;
	
	public LivroDTO() {
		
	}
	
	public LivroDTO(Livro entity) {
		BeanUtils.copyProperties(entity, this);
		
	}
	
	

}
