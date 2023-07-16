package com.simplelibrary.dto.min;

import org.springframework.beans.BeanUtils;

import com.simplelibrary.entities.Adm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdmMinDTO {
	
	private Integer id;
	private String nome;
	
	public AdmMinDTO() {
	}
	
	public AdmMinDTO(Adm entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
