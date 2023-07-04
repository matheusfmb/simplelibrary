package com.simplelibrary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplelibrary.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail (String email);
}
