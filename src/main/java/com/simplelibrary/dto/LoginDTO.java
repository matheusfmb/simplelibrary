package com.simplelibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	@NotNull(message="Email não pode ser nula")
	@Email(message="Email Inválido")
	private String email;
	
	@NotNull(message="Senha não pode ser nula")
	private String senha;
}
