package com.simplelibrary.dto;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Livro;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {
	
	private Integer idLivro;
	
	@NotNull(message="Nome do Livro não pode ser nulo")
	private String nomeLivro;
	
	@NotNull(message="Ano lancamento não pode ser nulo")
	private Integer anoLancamento;
	
	@NotNull(message="Descrição curta não pode ser nula")
	private String shortDescription;
	
	@NotNull(message="Descrição longa não pode ser curta")
	private String longDescription;
	
	@NotNull(message="Quantidade de Exemplares do Livro não pode ser nulo")
	private Integer qtdExemplares;
	
	@NotNull(message="url da Imagem não pode ser nulo")
	private String imgUrl;
	
	public LivroDTO() {
		
	}
	
	public LivroDTO(Livro entity) {
		BeanUtils.copyProperties(entity, this);
		
	}
	
	

}
