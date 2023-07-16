package com.simplelibrary.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.simplelibrary.dto.EmprestimosDTO;
import com.simplelibrary.dto.min.AdmMinDTO;
import com.simplelibrary.dto.min.LivroMinDTO;
import com.simplelibrary.dto.min.UsuarioMinDTO;
import com.simplelibrary.entities.Emprestimos;
import com.simplelibrary.repositories.EmprestimoRepository;
import com.simplelibrary.repositories.LivroRepository;
import com.simplelibrary.repositories.UsuarioRepository;

@Service
public class EmprestimosService {
	
	@Autowired
	private EmprestimoRepository empRepository;
	@Autowired
	private UsuarioRepository usRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	
//  ADM - Listar todos os Empréstimos.
	@Transactional(readOnly = true)
	public ResponseEntity<List<EmprestimosDTO>>listarEmprestimos(){
		try {
			List<Emprestimos> emprestimos = empRepository.findAll();
			List<EmprestimosDTO> emprestimosDTO = listEmprestimoToEmprestimoDTO(emprestimos);
			return ResponseEntity.status(HttpStatus.OK).body(emprestimosDTO);

		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//  ADM - Listar Emprestimos por Usuário ID.
	@Transactional(readOnly= true)
	public ResponseEntity<List<EmprestimosDTO>>listarEmprestimosByUsuario(@PathVariable Integer id){
		try {
			List<Emprestimos> emprestimos = empRepository.FindByUsuarioId(id);
			List<EmprestimosDTO> emprestimosDTO = listEmprestimoToEmprestimoDTO(emprestimos);
			return ResponseEntity.status(HttpStatus.OK).body(emprestimosDTO);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

//  MÉTODO EXTRAIDO. CONVERSÃO DE LIST<Emprestimos> para List<Emprestimos> DTO.
	private List<EmprestimosDTO> listEmprestimoToEmprestimoDTO(List<Emprestimos> emprestimos) {
		List<EmprestimosDTO> emprestimosDTO = emprestimos.stream().map(emprestimo -> {
				EmprestimosDTO emprestimoDTO = new EmprestimosDTO(emprestimo);
				UsuarioMinDTO usuarioDTO = new UsuarioMinDTO(emprestimo.getUsuario());
				emprestimoDTO.setUsuario(usuarioDTO);				
				LivroMinDTO livroDTO = new LivroMinDTO(emprestimo.getLivro());
				emprestimoDTO.setLivro(livroDTO);				
				AdmMinDTO admDTO = new AdmMinDTO(emprestimo.getAdm());
				emprestimoDTO.setAdm(admDTO);					
				return emprestimoDTO;
				}).collect(Collectors.toList());
		return emprestimosDTO;
	}
	
	
	
}
	

	
	
	
	


