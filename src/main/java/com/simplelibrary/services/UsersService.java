package com.simplelibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.entities.Usuario;
import com.simplelibrary.repositories.AdmRepository;
import com.simplelibrary.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsersService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdmRepository admRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public ResponseEntity<UsuarioDTO> cadastrarUsuario (@Valid @RequestBody UsuarioDTO usuarioDTO){
		String senhaEncoder = passwordEncoder.encode(usuarioDTO.getSenha());
		Usuario novoUsuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getCpf(),usuarioDTO.getTelefone(),usuarioDTO.getEmail(),senhaEncoder);
		usuarioRepository.save(novoUsuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
	}

}
