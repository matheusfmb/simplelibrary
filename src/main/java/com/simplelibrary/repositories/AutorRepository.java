package com.simplelibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplelibrary.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

}
