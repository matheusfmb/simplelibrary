package com.simplelibrary.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplelibrary.dto.CategoriaDTO;
import com.simplelibrary.entities.Categoria;
import com.simplelibrary.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly=true)
	public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaDTO> categoriasDTO = categorias.stream().map(categoria ->{
			CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
			return categoriaDTO;}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(categoriasDTO);
		}
}

