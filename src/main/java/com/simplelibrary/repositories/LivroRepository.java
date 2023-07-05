package com.simplelibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplelibrary.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
	
	

}
