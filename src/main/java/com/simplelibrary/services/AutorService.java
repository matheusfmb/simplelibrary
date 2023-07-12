package com.simplelibrary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.simplelibrary.dto.AutorDTO;
import com.simplelibrary.dto.min.AutorMinDTO;
import com.simplelibrary.entities.Autor;
import com.simplelibrary.repositories.AutorRepository;
import jakarta.validation.Valid;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	
//	Listar Autores
	@Transactional(readOnly=true)
	public ResponseEntity<List<AutorMinDTO>> litarAutores(){
		try {
			List<Autor> autores = autorRepository.findAll();
			List<AutorMinDTO> autoresDTO = autores.stream().map(autor ->{
				AutorMinDTO autorMinDTO = new AutorMinDTO(autor);
				return autorMinDTO;}).collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(autoresDTO);
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();				
		}
	}
	

	@Transactional(readOnly = true)
	public ResponseEntity<AutorDTO> listarAutor(@PathVariable Integer id){
		try {
			Optional<Autor> autor = autorRepository.findById(id);
			if(autor.isPresent()) {
				AutorDTO autorDTO = new AutorDTO(autor.get());
				return ResponseEntity.status(HttpStatus.OK).body(autorDTO);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
//  ADM.	
	public ResponseEntity<AutorDTO> cadastrarAutor(@Valid @RequestBody AutorDTO autorCadastroDTO){
		Optional<Autor> autor = autorRepository.findByNomeAutor(autorCadastroDTO.getNomeAutor());
		if(!autor.isPresent()) {
			ResponseEntity.status(HttpStatus.CONFLICT).build();
		}else {
			Autor novoAutor = new Autor();
			novoAutor.setAutorDescricao(autorCadastroDTO.getAutorDescricao());
			novoAutor.setImgUrl(autorCadastroDTO.getImgUrl());
			novoAutor.setNacionalidade(autorCadastroDTO.getNacionalidade());
			novoAutor.setNomeAutor(autorCadastroDTO.getNomeAutor());
			autorRepository.save(novoAutor);
			AutorDTO autorReturn = new AutorDTO(novoAutor);
			return ResponseEntity.status(HttpStatus.CREATED).body(autorReturn);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}

