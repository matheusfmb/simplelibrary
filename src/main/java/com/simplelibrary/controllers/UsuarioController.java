package com.simplelibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplelibrary.dto.LoginDTO;
import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.services.UsersService;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {
		
	@Autowired
	private UsersService usersService;
	
	
//	Cadastro de usuário
	@PostMapping("api/cadastro")
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
		return usersService.cadastrarUsuario(usuarioDTO);
	}
	
//  Login de usuário
	@PostMapping("api/login")
	public ResponseEntity<?> usersLogin(@Valid @RequestBody LoginDTO loginDTO){
		return usersService.loginUsuarios(loginDTO);
	}
	
// listar Usuário - perfil para outro usuário
	@GetMapping("api/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> listarUsuarioPublico(@PathVariable Integer id){
		return usersService.listarUsuarioPublico(id);
	}
	
}
