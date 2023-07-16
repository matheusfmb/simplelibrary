package com.simplelibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simplelibrary.entities.Emprestimos;

public interface EmprestimoRepository extends JpaRepository<Emprestimos, Integer> {
	
	@Query(value = "SELECT * FROM emprestimos WHERE id_usuario = ?1", nativeQuery = true)
	List<Emprestimos> FindByUsuarioId(Integer id);
	
	@Query(value = "SELECT * FROM emprestimos WHERE status = ?1", nativeQuery = true)
	List<Emprestimos> FindByStatus(String status);
}
