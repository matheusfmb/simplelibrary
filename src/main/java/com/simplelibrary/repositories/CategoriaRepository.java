package com.simplelibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplelibrary.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
