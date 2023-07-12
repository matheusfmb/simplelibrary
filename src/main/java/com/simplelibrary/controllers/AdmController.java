package com.simplelibrary.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.simplelibrary.dto.AutorDTO;
import com.simplelibrary.dto.AvaliacaoDTO;
import com.simplelibrary.dto.CategoriaDTO;
import com.simplelibrary.dto.LivroDTO;
import com.simplelibrary.dto.UsuarioDTO;
import com.simplelibrary.dto.create.LivroCadastroDTO;
import com.simplelibrary.dto.update.LivroUpdateDTO;
import com.simplelibrary.entities.Adm;
import com.simplelibrary.services.AdmService;
import com.simplelibrary.services.AutorService;
import com.simplelibrary.services.AvaliacaoService;
import com.simplelibrary.services.CategoriaService;
import com.simplelibrary.services.LivroService;

import jakarta.validation.Valid;

@RestController
public class AdmController {
	
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private AvaliacaoService avaliacaoService;
	@Autowired
	private AutorService autorService;
	@Autowired
	private AdmService admService;	
	@Autowired
	private LivroService livroService;
	
//  ADM - LISTAR USUÁRIOS PARA ADM.
	@GetMapping("api/adm/usuarios")
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
		return admService.listarUsuariosParaAdm();
	}
	
//  ADM - LISTAR USUÁRIO ESPECÍFICO PARA ADM. COM TODAS INFORMAÇÕES.
	@GetMapping("api/adm/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> listarUsuarioParaAdm(@PathVariable Integer id){
		return admService.listarUsuarioParaAdm(id);
	}
	
//  ADM - CADASTRO DE ADM
	@PostMapping("api/adm")
	public ResponseEntity<Adm> cadastrarAdm(@Valid @RequestBody Adm adm){
		return admService.cadastrarAdm(adm);
	}

//  ADM - CADASTRO DE CATEGORIA
	@PostMapping("api/adm/categorias")
	public ResponseEntity<CategoriaDTO> cadastrarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
		return categoriaService.cadastrarCategoria(categoriaDTO);
	}
	
//  ADM - CADASTRO DE LIVRO
	@PostMapping("api/adm/livros")
	public ResponseEntity<LivroDTO> cadastrarLivro(@Valid @RequestBody LivroCadastroDTO livrocadastroDTO){
		return livroService.cadastrarLivro(livrocadastroDTO);
	}
	
//  ADM - ATUALIZAR INFORMAÇÕES DO LIVRO
	@PutMapping("api/adm/livros/{id}")
	public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Integer id, @Valid @RequestBody LivroUpdateDTO livroUpdateDTO){
		return livroService.atualizarLivro(id, livroUpdateDTO);
	}
	
//	ADM - CADASTRAR AUTOR.
	@PostMapping("api/adm/autores")
	public ResponseEntity<AutorDTO> cadastroAutor(@Valid @RequestBody AutorDTO autorCadastroDTO){
		return autorService.cadastrarAutor(autorCadastroDTO);
	}
	
// 	ADM - LISTAR AVALIAÇÕES DO USUÁRIO ESPECÍFICO.
	@GetMapping("api/adm/avaliacoes/usuarios/{id}")
	public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesByUsuario(@PathVariable Integer id){
		return avaliacaoService.listarAvaliacaoByUsuario(id);
	}
	
	
}
