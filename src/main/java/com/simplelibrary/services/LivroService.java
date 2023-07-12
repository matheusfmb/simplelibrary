package com.simplelibrary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.simplelibrary.dto.CategoriaDTO;
import com.simplelibrary.dto.LivroDTO;
import com.simplelibrary.dto.create.LivroCadastroDTO;
import com.simplelibrary.dto.min.AutorMinDTO;
import com.simplelibrary.dto.min.LivroMinDTO;
import com.simplelibrary.dto.update.LivroUpdateDTO;
import com.simplelibrary.entities.Autor;
import com.simplelibrary.entities.Categoria;
import com.simplelibrary.entities.Livro;
import com.simplelibrary.repositories.AutorRepository;
import com.simplelibrary.repositories.CategoriaRepository;
import com.simplelibrary.repositories.LivroRepository;

import jakarta.validation.Valid;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
//	LIVRO POR CATEGORIA ID
	@Transactional(readOnly = true)
	public ResponseEntity<List<LivroMinDTO>> listarLivroPorCategoria(@PathVariable Integer id){
		try {
			List<Livro> livros = livroRepository.findByCategoriaId(id);
			if(livros == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}else if(livros.isEmpty()){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else{
				List<LivroMinDTO> livrosDTO = livros.stream().map(livro -> {
					LivroMinDTO livroDTO = new LivroMinDTO(livro);	
				
					List<AutorMinDTO> autoresDTO = livro.getAutor()
						    .stream()
						    .map(AutorMinDTO::new)
						    .collect(Collectors.toList());
					livroDTO.setAutor(autoresDTO);
			        
					List<CategoriaDTO> categoriasDTO = livro.getCategoria()
							.stream()
							.map(CategoriaDTO::new)	
							.collect(Collectors.toList());
					livroDTO.setCategoria(categoriasDTO);
			        
					return livroDTO;
					
				}).collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(livrosDTO);
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	Livro por Autor
	@Transactional(readOnly = true)
	public ResponseEntity<List<LivroMinDTO>> listarLivroPorAutor(@PathVariable Integer id){
		List<Livro> livros = livroRepository.findByAutorId(id);
		if(livros == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else if (livros.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			List<LivroMinDTO> livrosMinDTO = livros.stream().map(livro -> {
				LivroMinDTO livroMinDTO = new LivroMinDTO(livro);	

				List<AutorMinDTO> autoresDTO = livro.getAutor()
					    .stream()
					    .map(AutorMinDTO::new)
					    .collect(Collectors.toList());
				livroMinDTO.setAutor(autoresDTO);
		        
				List<CategoriaDTO> categoriasDTO = livro.getCategoria()
						.stream()
						.map(CategoriaDTO::new)	
						.collect(Collectors.toList());
				livroMinDTO.setCategoria(categoriasDTO);
				
				return livroMinDTO;
				
			}).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(livrosMinDTO);
		}
		
	}
	
	
//	Listagem para catálogo Mínimas informações.
	@Transactional(readOnly = true)
	public ResponseEntity<List<LivroMinDTO>> listarLivrosMin(){
		try {
			List<Livro> livros = livroRepository.findAll();
			if(livros.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
				List<LivroMinDTO> livrosMinDTO = livros.stream().map(livro -> {
					LivroMinDTO livroMinDTO = new LivroMinDTO(livro);	

					List<AutorMinDTO> autoresDTO = livro.getAutor()
						    .stream()
						    .map(AutorMinDTO::new)
						    .collect(Collectors.toList());
					livroMinDTO.setAutor(autoresDTO);
			        
					List<CategoriaDTO> categoriasDTO = livro.getCategoria()
							.stream()
							.map(CategoriaDTO::new)	
							.collect(Collectors.toList());
					livroMinDTO.setCategoria(categoriasDTO);
					
					return livroMinDTO;
					
				}).collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(livrosMinDTO);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	Listagem específica.
	@Transactional(readOnly = true)
	public ResponseEntity<LivroDTO> listarLivro(@PathVariable Integer id){
		try {
			Optional<Livro> livro = livroRepository.findById(id);
			if(livro.isPresent()) {
				LivroDTO livroDTO = new LivroDTO(livro.get());
				
				List<AutorMinDTO> autoresDTO = livro.get().getAutor()
					    .stream()
					    .map(AutorMinDTO::new)
					    .collect(Collectors.toList());
				livroDTO.setAutor(autoresDTO);
		        
				List<CategoriaDTO> categoriasDTO = livro.get().getCategoria()
						.stream()
						.map(CategoriaDTO::new)	
						.collect(Collectors.toList());
				livroDTO.setCategoria(categoriasDTO);
		        return ResponseEntity.status(HttpStatus.OK).body(livroDTO);
				
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
//  ADM.
	@Transactional
	public ResponseEntity<?> cadastrarLivro(@Valid @RequestBody LivroCadastroDTO livroCadastroDTO) {
		try {
			Livro isCreated = livroRepository.findByNomeLivro(livroCadastroDTO.getNomeLivro());
			if (isCreated != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			Livro livro = new Livro();
			livro.setAnoLancamento(livroCadastroDTO.getAnoLancamento());
			livro.setImgUrl(livroCadastroDTO.getImgUrl());
			livro.setLongDescription(livroCadastroDTO.getLongDescription());
			livro.setNomeLivro(livroCadastroDTO.getNomeLivro());
			livro.setQtdExemplares(livroCadastroDTO.getQtdExemplares());
			livro.setShortDescription(livroCadastroDTO.getShortDescription());
			
			for(Integer idAutor : livroCadastroDTO.getIdAutores()) {
				Autor autor = autorRepository.findById(idAutor).orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
	            livro.getAutor().add(autor);
			}
			
			for(Integer idCategoria : livroCadastroDTO.getIdCategorias()) {
				Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado"));
				livro.getCategoria().add(categoria);
			}
			
			livroRepository.save(livro);
			
			LivroDTO livroDTO = new LivroDTO(livro);
			
			List<AutorMinDTO> autoresDTO = livro.getAutor()
				    .stream()
				    .map(AutorMinDTO::new)
				    .collect(Collectors.toList());
			livroDTO.setAutor(autoresDTO);
	        
			List<CategoriaDTO> categoriasDTO = livro.getCategoria()
					.stream()
					.map(CategoriaDTO::new)	
					.collect(Collectors.toList());
			livroDTO.setCategoria(categoriasDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(livroDTO);
	        
		}catch (IllegalArgumentException e1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
		}catch (Exception e2) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
//  ADM.
	@Transactional
	public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Integer id, @Valid @RequestBody LivroUpdateDTO livroUpdateDTO) {
		try {
			Optional<Livro> livro = livroRepository.findById(id);
			if(livro.isPresent()) {
				BeanUtils.copyProperties(livroUpdateDTO, livro.get());
				livroRepository.save(livro.get());
				LivroDTO livroDTO = new LivroDTO(livro.get());
				return ResponseEntity.status(HttpStatus.OK).body(livroDTO);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	
	}
	
}
