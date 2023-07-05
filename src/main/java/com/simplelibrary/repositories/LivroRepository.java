package com.simplelibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.simplelibrary.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
	
    Livro findByNomeLivro(String nome);
	
	@Query(value = "SELECT * FROM livro l JOIN livro_has_categoria cl ON l.id_livro = cl.livro_id WHERE cl.categoria_id = ?1", nativeQuery = true)
    List<Livro> findByCategoriaId(Integer id);
	
	@Query(value = "SELECT * FROM livro l JOIN livro_has_autor la ON l.id_livro = la.livro_id WHERE la.autor_id = ?1", nativeQuery = true)
    List<Livro> findByAutorId(Integer id);
	

}
