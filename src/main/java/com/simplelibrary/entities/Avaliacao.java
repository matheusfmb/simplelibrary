package com.simplelibrary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="avaliacao")
@Getter
@Setter
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idAvalicao;
	
	private Double scoreLivro;
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_livro")
	private Livro livro;

	
	public Avaliacao() {
		
	}
	
	public Avaliacao(Double scoreLivro, String comentario, Usuario usuario, Livro livro) {
		this.scoreLivro = scoreLivro;
		this.comentario = comentario;
		this.usuario = usuario;
		this.livro = livro;
	}


}