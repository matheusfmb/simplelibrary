package com.simplelibrary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.simplelibrary.dto.LoginDTO;
import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.entities.Adm;
import com.simplelibrary.entities.Usuario;
import com.simplelibrary.repositories.AdmRepository;
import com.simplelibrary.repositories.UsuarioRepository;
import com.simplelibrary.security.Token;
import com.simplelibrary.security.TokenUtil;

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
		UsuarioDTO novoUsuarioDTO = new UsuarioDTO(novoUsuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarioDTO);
	}
	
	public ResponseEntity<Adm> cadastroAdm(@RequestBody Adm adm){
		String senhaEncoder = passwordEncoder.encode(adm.getSenha());
		Adm admNovo = new Adm(adm.getNome(), adm.getCpf(), adm.getTelefone(),adm.getEmail(),senhaEncoder);
		admRepository.save(admNovo);
		return ResponseEntity.status(HttpStatus.CREATED).body(admNovo);
	}
	
	public ResponseEntity<?> loginUsuarios(@Valid @RequestBody LoginDTO loginDTO){
		Optional<Usuario> usuario = usuarioRepository.findByEmail(loginDTO.getEmail());
		if(usuario.isPresent()){
			if(passwordEncoder.matches(loginDTO.getSenha(), usuario.get().getSenha())){
				UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.get());
				List<String> permissions = new ArrayList<>();
				permissions.add("USER");
				String tokenAuth = TokenUtil.createToken(usuario.get().getEmail(),permissions);
				Token token = new Token(tokenAuth);
				System.out.println(token.getToken());
				return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION,token.getToken()).body(usuarioDTO);
			}
		}else {
			Optional<Adm> adm = admRepository.findByEmail(loginDTO.getEmail());
			if(adm.isPresent()) {
				if(passwordEncoder.matches(loginDTO.getSenha(), adm.get().getSenha())) {
					List<String> permissions = new ArrayList<>();
					permissions.add("ADMIN");
					String tokenAuth = TokenUtil.createToken(adm.get().getEmail(),permissions);
					Token token = new Token(tokenAuth);
					System.out.println(token.getToken());
					return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION,token.getToken()).body(adm);
				}
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário ou Senha incorretos");
		
	}

}
