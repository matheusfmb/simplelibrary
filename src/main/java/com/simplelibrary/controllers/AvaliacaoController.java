package com.simplelibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simplelibrary.dto.AvaliacaoDTO;
import com.simplelibrary.services.AvaliacaoService;

@RestController
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@GetMapping("api/avaliacao/livro/{id}")
	public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacaoByLivroId(@PathVariable Integer id){
		return avaliacaoService.listarAvaliacaoByLivroId(id);
	}
	

}
