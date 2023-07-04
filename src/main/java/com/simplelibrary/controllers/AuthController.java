package com.simplelibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplelibrary.dto.LoginDTO;
import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.entities.Adm;
import com.simplelibrary.services.UsersService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
		
	@Autowired
	private UsersService usersService;
		
	@PostMapping("api/usuarios")
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
		return usersService.cadastrarUsuario(usuarioDTO);
	}
	
	@PostMapping("api/adm")
	public ResponseEntity<Adm> cadastrarUsuario(@Valid @RequestBody Adm adm){
		return usersService.cadastroAdm(adm);
	}
	
	@PostMapping("api/login")
	public ResponseEntity<?> usersLogin(@Valid @RequestBody LoginDTO loginDTO){
		return usersService.loginUsuarios(loginDTO);
	}
	
	
	@GetMapping("api/adm")
	public String adm () {
		return "endpoint do adm";
		
	}
	
	@GetMapping("api/user")
	public String user () {
		return "endpoint do usuário";
	}

}
