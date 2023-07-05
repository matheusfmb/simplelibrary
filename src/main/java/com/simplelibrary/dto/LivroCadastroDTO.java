package com.simplelibrary.dto;

import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroCadastroDTO {
	
	@NotNull(message = "O nome do livro não pode ser nulo.")
    @Size(min = 1, max = 100, message = "O nome do livro deve ter entre 1 e 100 caracteres.")
    private String nomeLivro;

    @NotNull(message = "O ano de lançamento não pode ser nulo.")
    @Min(value = 1800, message = "O ano de lançamento deve ser igual ou superior a 1.")
    @Max(value = 2023, message = "O ano de lançamento não pode ser superior a 2023.")
    private Integer anoLancamento;

    @NotNull(message="A descrição curta não pode ser Nula")
    private String shortDescription;

    @NotNull(message="A descrição longa não pode ser Nula")
    private String longDescription;

    @NotNull(message = "A URL da imagem não pode ser nula.")
    @URL(message = "A URL da imagem não é válida.")
    private String imgUrl;

    @NotNull(message = "A quantidade de exemplares não pode ser nula.")
    @Min(value = 1, message = "A quantidade de exemplares deve ser igual ou superior a 1.")
    private Integer qtdExemplares;

    @NotNull(message = "A lista de IDs dos autores não pode ser nula.")
    @NotEmpty(message = "A lista de IDs dos autores não pode estar vazia.")
    private Set<Integer> idAutores;

    @NotNull(message = "A lista de IDs das categorias não pode ser nula.")
    @NotEmpty(message = "A lista de IDs das categorias não pode estar vazia.")
    private Set<Integer> idCategorias;
	
	public LivroCadastroDTO() {
		
	}
	
	public LivroCadastroDTO( String nomeLivro, Integer anoLancamento, String shortDescription,
			String longDescription, String imgUrl, Integer qtdExemplares, Set<Integer> idAutores, Set<Integer> idCategorias) {
		this.nomeLivro = nomeLivro;
		this.anoLancamento = anoLancamento;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.imgUrl = imgUrl;
		this.qtdExemplares = qtdExemplares;
		this.idAutores = idAutores;
		this.idCategorias = idCategorias;
	}
	
}
