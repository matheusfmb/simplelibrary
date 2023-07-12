package com.simplelibrary.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.simplelibrary.dto.AvaliacaoDTO;
import com.simplelibrary.dto.min.LivroMinDTO;
import com.simplelibrary.entities.Avaliacao;
import com.simplelibrary.entities.Livro;
import com.simplelibrary.entities.Usuario;
import com.simplelibrary.repositories.AvaliacaoRepository;
import com.simplelibrary.repositories.LivroRepository;
import com.simplelibrary.repositories.UsuarioRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
//  Avaliações por livro livro - público.
	@Transactional(readOnly=true)
	public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacaoByLivroId(@PathVariable Integer id){
		Livro livro = livroRepository.findById(id).get();
		List<Avaliacao> avaliacaoByLivro = avaliacaoRepository.findByLivro(livro);
		List<AvaliacaoDTO> avaliacaoByLivroDTO = avaliacaoByLivro.stream().map(avaliacao ->{
			AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(avaliacao);
			LivroMinDTO livroDTO = new LivroMinDTO(avaliacao.getLivro());
			avaliacaoDTO.setLivro(livroDTO);
			avaliacaoDTO.getUsuario().setSenha("");
			avaliacaoDTO.getUsuario().setCpf("");
			avaliacaoDTO.getUsuario().setTelefone("");
			return avaliacaoDTO;}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(avaliacaoByLivroDTO); 
	}
	
//	ADM.
	@Transactional(readOnly = true)
	public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacaoByUsuario(@PathVariable Integer id){
		Usuario usuario = usuarioRepository.findById(id).get();
		List<Avaliacao> avaliacaoByUsuario = avaliacaoRepository.findByUsuario(usuario);
		List<AvaliacaoDTO> avaliacaoByLivroDTO = avaliacaoByUsuario.stream().map(avaliacao ->{
			AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(avaliacao);
			LivroMinDTO livroDTO = new LivroMinDTO(avaliacao.getLivro());
			avaliacaoDTO.setLivro(livroDTO);
			avaliacaoDTO.getUsuario().setSenha("");
			return avaliacaoDTO;}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(avaliacaoByLivroDTO); 
	}
	

}
