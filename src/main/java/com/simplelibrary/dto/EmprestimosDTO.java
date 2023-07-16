package com.simplelibrary.dto;

import java.util.Date;
import org.springframework.beans.BeanUtils;
import com.simplelibrary.dto.min.AdmMinDTO;
import com.simplelibrary.dto.min.LivroMinDTO;
import com.simplelibrary.dto.min.UsuarioMinDTO;
import com.simplelibrary.entities.Emprestimos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmprestimosDTO {
	
	private Integer idEmprestimo;
	private Date dtEmprestimo;
	private Date dtDevolucao;
	private String status;
	
	private UsuarioMinDTO usuario;
	private LivroMinDTO livro;
	private AdmMinDTO adm;

	public EmprestimosDTO() {
		
	}
	public EmprestimosDTO(Emprestimos entity) {
		BeanUtils.copyProperties(entity, this);
			
	}

}
