package com.simplelibrary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.simplelibrary.dto.AutorMinDTO;
import com.simplelibrary.dto.LivroDTO;
import com.simplelibrary.dto.LivroMinDTO;
import com.simplelibrary.entities.Categoria;
import com.simplelibrary.entities.Livro;
import com.simplelibrary.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	
//	LIVRO POR CATEGORIA ID
	@Transactional(readOnly = true)
	public ResponseEntity<List<LivroDTO>> listarLivroPorCategoria(@PathVariable Integer id){
		List<Livro> livros = livroRepository.findByCategoriaId(id);
		List<LivroDTO> livrosDTO = livros.stream().map(livro -> {
			LivroDTO livroDTO = new LivroDTO(livro);	
			
			List<AutorMinDTO> autoresDTO = new ArrayList<>();
	        livro.getAutor().forEach(autor -> {
	            AutorMinDTO autorDTO = new AutorMinDTO(autor);
	            autoresDTO.add(autorDTO);
	        });
	        livroDTO.setAutor(autoresDTO);
	        
	        List<Categoria> categoriasDTO = new ArrayList<>();
	        livro.getCategoria().forEach(categoria -> {
	            Categoria categoriaDTO = new Categoria(categoria.getIdCategoria(),categoria.getNomeCategoria());
	            categoriasDTO.add(categoriaDTO);
	        });
	        livroDTO.setCategoria(categoriasDTO);
			return livroDTO;
			
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(livrosDTO);
	}
	
//	Livro por Autor
	@Transactional(readOnly = true)
	public ResponseEntity<List<LivroDTO>> listarLivroPorAutor(@PathVariable Integer id){
		List<Livro> livros = livroRepository.findByAutorId(id);
		List<LivroDTO> livrosDTO = livros.stream().map(livro -> {
			LivroDTO livroDTO = new LivroDTO(livro);	
			
			List<AutorMinDTO> autoresDTO = new ArrayList<>();
	        livro.getAutor().forEach(autor -> {
	            AutorMinDTO autorDTO = new AutorMinDTO(autor);
	            autoresDTO.add(autorDTO);
	        });
	        livroDTO.setAutor(autoresDTO);
	        
	        List<Categoria> categoriasDTO = new ArrayList<>();
	        livro.getCategoria().forEach(categoria -> {
	            Categoria categoriaDTO = new Categoria(categoria.getIdCategoria(),categoria.getNomeCategoria());
	            categoriasDTO.add(categoriaDTO);
	        });
	        livroDTO.setCategoria(categoriasDTO);
			return livroDTO;
			
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(livrosDTO);
		
	}
	
	
//	Listagem para catálogo Mínimas informações.
	@Transactional(readOnly = true)
	public ResponseEntity<List<LivroMinDTO>> listarLivrosMin(){
		List<Livro> livros = livroRepository.findAll();
		List<LivroMinDTO> livrosMinDTO = livros.stream().map(livro -> {
			LivroMinDTO livroMinDTO = new LivroMinDTO(livro);	
			
			List<AutorMinDTO> autoresDTO = new ArrayList<>();
	        livro.getAutor().forEach(autor -> {
	            AutorMinDTO autorDTO = new AutorMinDTO(autor);
	            autoresDTO.add(autorDTO);
	        });
	        livroMinDTO.setAutor(autoresDTO);
	        
	        List<Categoria> categoriasDTO = new ArrayList<>();
	        livro.getCategoria().forEach(categoria -> {
	            Categoria categoriaDTO = new Categoria(categoria.getIdCategoria(),categoria.getNomeCategoria());
	            categoriasDTO.add(categoriaDTO);
	        });
	        livroMinDTO.setCategoria(categoriasDTO);
			return livroMinDTO;
			
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(livrosMinDTO);
				
	}
	
//	Listagem específica.
	@Transactional(readOnly = true)
	public ResponseEntity<LivroDTO> listarLivro(@PathVariable Integer id){
		Optional<Livro> livro = livroRepository.findById(id);
		if(livro.isPresent()) {
			LivroDTO livroDTO = new LivroDTO(livro.get());
			
			List<AutorMinDTO> autoresDTO = new ArrayList<>();
	        livro.get().getAutor().forEach(autor -> {
	            AutorMinDTO autorDTO = new AutorMinDTO(autor);
	            autoresDTO.add(autorDTO);
	        });
	        livroDTO.setAutor(autoresDTO);
	        
	        List<Categoria> categoriasDTO = new ArrayList<>();
	        livro.get().getCategoria().forEach(categoria -> {
	            Categoria categoriaDTO = new Categoria(categoria.getIdCategoria(),categoria.getNomeCategoria());
	            categoriasDTO.add(categoriaDTO);
	        });
	        livroDTO.setCategoria(categoriasDTO);
	        return ResponseEntity.status(HttpStatus.OK).body(livroDTO);
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
}
