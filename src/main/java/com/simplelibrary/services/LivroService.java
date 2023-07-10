package com.simplelibrary.services;

import java.util.ArrayList;
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

import com.simplelibrary.dto.AutorMinDTO;
import com.simplelibrary.dto.LivroCadastroDTO;
import com.simplelibrary.dto.LivroDTO;
import com.simplelibrary.dto.LivroMinDTO;
import com.simplelibrary.dto.LivroUpdateDTO;
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
	
//  ADM.
	@Transactional
	public ResponseEntity<LivroDTO> cadastrarLivro(@Valid @RequestBody LivroCadastroDTO livroCadastroDTO) {
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
		
        return ResponseEntity.status(HttpStatus.CREATED).body(livroDTO);
	}
	
	
//  ADM.
	public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Integer id, @Valid @RequestBody LivroUpdateDTO livroUpdateDTO) {
		Optional<Livro> livro = livroRepository.findById(id);
		if(livro.isPresent()) {
			BeanUtils.copyProperties(livroUpdateDTO, livro.get());
			try {
				livroRepository.save(livro.get());
				LivroDTO livroDTO = new LivroDTO(livro.get());
				return ResponseEntity.status(HttpStatus.OK).body(livroDTO);
			}catch(Exception e){
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
}
