package com.simplelibrary.dto;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	private Integer id;
	
	@NotNull(message="Nome não pode ser Nulo")
	@Size(max=60)
	private String nome;
	
	@NotNull(message="Cpf não pode ser Nulo")
	@Size(max=17)
	private String cpf;
	
	@NotNull(message="Telefone não pode ser Nulo")
	private String telefone;
	
	@NotNull(message="Email não pode ser Nulo")
	@Size(max=50)
	@Email
	private String email;
	
	@NotNull(message="Senha não pode ser Nulo")
	@Size(max=50)
	private String senha;
	
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
