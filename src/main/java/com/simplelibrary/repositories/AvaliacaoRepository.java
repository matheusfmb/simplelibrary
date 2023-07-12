package com.simplelibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplelibrary.entities.Avaliacao;
import com.simplelibrary.entities.Livro;
import com.simplelibrary.entities.Usuario;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
	List<Avaliacao> findByUsuario(Usuario usuario);
	List<Avaliacao> findByLivro(Livro livro);
}
