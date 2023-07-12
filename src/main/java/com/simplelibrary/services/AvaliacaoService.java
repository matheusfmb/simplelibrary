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
import com.simplelibrary.entities.Avaliacao;
import com.simplelibrary.entities.Livro;
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
			avaliacaoDTO.getUsuario().setSenha(null);
			avaliacaoDTO.getUsuario().setCpf(null);
			avaliacaoDTO.getUsuario().setTelefone(null);
			avaliacaoDTO.setIdLivro(livro.getIdLivro());
			return avaliacaoDTO;}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(avaliacaoByLivroDTO);
	}
}
