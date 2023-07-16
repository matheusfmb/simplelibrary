package com.simplelibrary.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="emprestimos")
@Getter
@Setter
public class Emprestimos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEmprestimo;
	
	private Date dtEmprestimo;
	private Date dtDevolucao;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_livro")
	private Livro livro;
	
	@ManyToOne
	@JoinColumn(name="id_adm")
	private Adm adm;

	public Emprestimos() {
		
	}
	
	public Emprestimos(Date dtEmprestimo, Date dtDevolucao, String status, Usuario usuario,
			Livro livro, Adm adm) {
		this.dtEmprestimo = dtEmprestimo;
		this.dtDevolucao = dtDevolucao;
		this.status = status;
		this.usuario = usuario;
		this.livro = livro;
		this.adm = adm; 
	}

}


