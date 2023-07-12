package com.simplelibrary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import com.simplelibrary.dto.CategoriaDTO;
import com.simplelibrary.entities.Categoria;
import com.simplelibrary.repositories.CategoriaRepository;

import jakarta.validation.Valid;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@Transactional(readOnly=true)
	public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
		try {
			List<Categoria> categorias = categoriaRepository.findAll();
			List<CategoriaDTO> categoriasDTO = categorias.stream().map(categoria ->{
				CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
				return categoriaDTO;}).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(categoriasDTO);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	
//	ADM
	@Transactional
	public ResponseEntity<CategoriaDTO> cadastrarCategoria(@Valid @RequestBody CategoriaDTO categoria){
		try {
			Optional<Categoria> categoriaExiste = categoriaRepository.findByNomeCategoria(categoria.getNomeCategoria());
			if(categoriaExiste.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}else{
				Categoria novaCategoria = new Categoria();
				novaCategoria.setNomeCategoria(categoria.getNomeCategoria());
				categoriaRepository.save(novaCategoria);
				CategoriaDTO categoriaDTO = new CategoriaDTO(novaCategoria);
				return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDTO);
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
}

