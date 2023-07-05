package com.simplelibrary.services;

import java.util.ArrayList;
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

import com.simplelibrary.dto.AutorMinDTO;
import com.simplelibrary.dto.LivroCadastroDTO;
import com.simplelibrary.dto.LivroDTO;
import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.entities.Adm;
import com.simplelibrary.entities.Autor;
import com.simplelibrary.entities.Categoria;
import com.simplelibrary.entities.Livro;
import com.simplelibrary.entities.Usuario;
import com.simplelibrary.repositories.AdmRepository;
import com.simplelibrary.repositories.AutorRepository;
import com.simplelibrary.repositories.CategoriaRepository;
import com.simplelibrary.repositories.LivroRepository;
import com.simplelibrary.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class AdmService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdmRepository admRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
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
	
//  ADM Cadastro de Livro.
	@Transactional
	public ResponseEntity<LivroDTO> cadastrarLivro(@Valid @RequestBody LivroCadastroDTO livroCadastroDTO) {
		Livro isCreated = livroRepository.findByNomeLivro(livroCadastroDTO.getNomeLivro());
		if (isCreated != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		Livro livro = new Livro();
		livro.setAnoLancamento(livroCadastroDTO.getAnoLancamento());
		livro.setImgUrl(livroCadastroDTO.getImgUrl());
		livro.setLongDescription(livroCadastroDTO.getLongDescription());
		livro.setNomeLivro(livroCadastroDTO.getNomeLivro());
		livro.setQtdExemplares(livroCadastroDTO.getQtdExemplares());
		livro.setShortDescription(livroCadastroDTO.getShortDescription());
		
		for(Integer idAutor : livroCadastroDTO.getIdAutores()) {
			Autor autor = autorRepository.findById(idAutor).orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
            livro.getAutor().add(autor);
		}
		
		for(Integer idCategoria : livroCadastroDTO.getIdCategorias()) {
			Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado"));
			livro.getCategoria().add(categoria);
		}
		
		livroRepository.save(livro);
		
		LivroDTO livroDTO = new LivroDTO(livro);
		List<AutorMinDTO> autoresDTO = new ArrayList<>();
        livro.getAutor().forEach(autor -> {
            AutorMinDTO autorDTO = new AutorMinDTO(autor);
            autoresDTO.add(autorDTO);
        });
        livroDTO.setAutor(autoresDTO);
        
        List<Categoria> categoriasDTO = new ArrayList<>();
        livro.getCategoria().forEach(categoria -> {
            Categoria categoriaDTO = new Categoria(categoria.getIdCategoria(),categoria.getNomeCategoria());
            categoriasDTO.add(categoriaDTO);
        });
        livroDTO.setCategoria(categoriasDTO);
		
        return ResponseEntity.status(HttpStatus.CREATED).body(livroDTO);
	}
	
	

}
