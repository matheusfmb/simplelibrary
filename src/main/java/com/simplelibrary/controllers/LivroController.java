package com.simplelibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simplelibrary.dto.CategoriaDTO;
import com.simplelibrary.dto.LivroDTO;
import com.simplelibrary.dto.min.LivroMinDTO;
import com.simplelibrary.services.CategoriaService;
import com.simplelibrary.services.LivroService;

@RestController
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private CategoriaService categoriaService;
	
//	Livros mínimo para listagem página.
	@GetMapping("api/livros")
	public ResponseEntity<List<LivroMinDTO>> listarLivros(){
		return livroService.listarLivrosMin();
	}

//	Livro específico.
	@GetMapping("api/livros/{id}")
	public ResponseEntity<LivroDTO> listarLivro(@PathVariable Integer id){
		return livroService.listarLivro(id);
	}
	
//  Listagem de livro por categoria
	@GetMapping("api/livros/categoria/{id}")
	public ResponseEntity<List<LivroDTO>> listarLivroPorCategoria(@PathVariable Integer id){
		return livroService.listarLivroPorCategoria(id);
	}
	
// Listagem de livro por Autor
	@GetMapping("api/livros/autor/{id}")
	public ResponseEntity<List<LivroDTO>> listarLivroPorAutor(@PathVariable Integer id){
		return livroService.listarLivroPorAutor(id);
	}

//  Listar categorias.
	@GetMapping("api/categoria")
	public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
		return categoriaService.listarCategorias();
	}
	
}
