package com.simplelibrary.dto.min;

import java.util.List;

import org.springframework.beans.BeanUtils;
import com.simplelibrary.dto.CategoriaDTO;
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
	private List<CategoriaDTO> categoria;
	
	public LivroMinDTO() {
		
	}
	
	public LivroMinDTO(Livro entity) {
		BeanUtils.copyProperties(entity, this);
	}

}
