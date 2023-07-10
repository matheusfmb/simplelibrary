package com.simplelibrary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplelibrary.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
	
	Optional<Autor> findByNomeAutor(String nome);

}
