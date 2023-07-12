package com.simplelibrary.dto;
import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Avaliacao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoDTO {
	
	private Integer idAvalicao;
	
	@NotNull(message="Nota não pode ser Nulo")
	private Double scoreLivro;
	
	@NotNull(message="Comentário não pode ser Nulo")
	private String comentario;
	
	@NotNull(message="Usuário não pode ser Nulo")
	private Integer idUsuario ;
	
	@NotNull(message="Livro não pode ser Nulo")
	private Integer livro;
	
	
	public AvaliacaoDTO() {
		
	}
	
	public AvaliacaoDTO(Avaliacao entity) {
		BeanUtils.copyProperties(entity, this);
		
	}
	

}
