package com.simplelibrary.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="livro")
@Getter
@Setter
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLivro;
	
	private String nomeLivro;
	
	private Integer anoLancamento;

	@Column(columnDefinition = "TEXT")
	private String shortDescription;

	@Column(columnDefinition = "TEXT")
	private String longDescription;
	
	private Integer qtdExemplares;
	
	private String imgUrl;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "livro_has_autor",
               joinColumns = @JoinColumn(name = "livro_id"),
               inverseJoinColumns = @JoinColumn(name = "autor_id"))
	private Set<Autor> autor = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "livro_has_categoria",
               joinColumns = @JoinColumn(name = "livro_id"),
               inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categoria = new HashSet<>();
	
	
	public Livro () {
		
	}
	
	public Livro(String nomeLivro, Integer anoLancamento, String shortDescription, 
			String longDescription, Integer qtdExemplares, String imgUrl, Set<Autor> autor, Set<Categoria> categoria) {
		this.nomeLivro = nomeLivro;
		this.anoLancamento = anoLancamento;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.qtdExemplares = qtdExemplares;
		this.imgUrl = imgUrl;
		this.autor = autor;
		this.categoria = categoria;
	}

}