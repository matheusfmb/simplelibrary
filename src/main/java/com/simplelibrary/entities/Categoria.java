package com.simplelibrary.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="categoria")
@Getter
@Setter
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	private String nomeCategoria;
	
	@ManyToMany(mappedBy = "categoria")
	private Set<Livro> livros = new HashSet<>();

	public Categoria() {
		
	}
	
	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
}
