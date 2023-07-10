package com.simplelibrary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.entities.Adm;
import com.simplelibrary.entities.Usuario;
import com.simplelibrary.repositories.AdmRepository;
import com.simplelibrary.repositories.UsuarioRepository;

@Service
public class AdmService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdmRepository admRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
//	ADM - Listagem de Usuários.
	@Transactional(readOnly = true)
	public ResponseEntity<List<UsuarioDTO>> listarUsuariosParaAdm(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario ->{
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			usuarioDTO.setSenha(null);
			return usuarioDTO;
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(usuariosDTO);
	}

//	ADM - Listagem de Usuário.
	@Transactional(readOnly = true)
	public ResponseEntity<UsuarioDTO> listarUsuarioParaAdm(@PathVariable Integer id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.get());
			usuarioDTO.setSenha(null);
			return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
	}
	
//	ADM - Cadastro de Adm
	@Transactional
	public ResponseEntity<Adm> cadastrarAdm(@RequestBody Adm adm){
		String senhaEncoder = passwordEncoder.encode(adm.getSenha());
		Adm admNovo = new Adm(adm.getNome(), adm.getCpf(), adm.getTelefone(),adm.getEmail(),senhaEncoder);
		admRepository.save(admNovo);
		return ResponseEntity.status(HttpStatus.CREATED).body(admNovo);
	}

}
