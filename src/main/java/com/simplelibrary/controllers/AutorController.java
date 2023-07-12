package com.simplelibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simplelibrary.dto.AutorDTO;
import com.simplelibrary.dto.min.AutorMinDTO;
import com.simplelibrary.services.AutorService;

@RestController
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	
//  Listar autores
	@GetMapping("api/autores")
	public ResponseEntity<List<AutorMinDTO>> listarAutores(){
		return autorService.litarAutores();
	}

//  Listar autor
	@GetMapping("api/autores/{id}")
	public ResponseEntity<AutorDTO> listarAutor(@PathVariable Integer id){
		return autorService.listarAutor(id);
	}
	
}
