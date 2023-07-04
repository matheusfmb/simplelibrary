package com.simplelibrary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplelibrary.entities.Adm;

public interface AdmRepository extends JpaRepository<Adm, Integer> {
	Optional<Adm> findByEmail(String email);

}
