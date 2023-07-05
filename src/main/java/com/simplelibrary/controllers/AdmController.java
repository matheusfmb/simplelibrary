package com.simplelibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.entities.Adm;
import com.simplelibrary.services.AdmService;

import jakarta.validation.Valid;

@RestController
public class AdmController {
	
	
	@Autowired
	private AdmService admService;
	
	@GetMapping("api/adm/usuarios")
	public ResponseEntity<List<UsuarioDTO>> listarUsuario(){
		return admService.listarUsuariosParaAdm();
	}
	
	@GetMapping("api/adm/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> listarUsuarioParaAdm(@PathVariable Integer id){
		return admService.listarUsuarioParaAdm(id);
	}
	
	@PostMapping("api/adm")
	public ResponseEntity<Adm> cadastrarAdm(@Valid @RequestBody Adm adm){
		return admService.cadastrarAdm(adm);
	}
}
