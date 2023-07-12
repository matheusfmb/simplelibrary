package com.simplelibrary.dto.update;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Livro;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LivroUpdateDTO {
	
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
    
    public LivroUpdateDTO() {
    	
    }
    
    public LivroUpdateDTO(Livro entity) {
    	BeanUtils.copyProperties(entity, this);
    }
}
