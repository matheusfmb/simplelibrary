package com.simplelibrary.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="autor")
@Getter
@Setter
public  class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAutor;
	private String nomeAutor;
	private String nacionalidade;
	private String imgUrl;
	
	@ManyToMany(mappedBy = "autor")
	private Set<Livro> livros = new HashSet<>();

	@Column(columnDefinition = "TEXT")
	private String autorDescricao;

	public Autor() {
		
	}
	
	public Autor(String nomeAutor, String nacionalidade, String autorDescricao,String imgUrl) {
		this.nomeAutor = nomeAutor;
		this.nacionalidade = nacionalidade;
		this.autorDescricao = autorDescricao;
		this.imgUrl = imgUrl;
	}


	
	
}
