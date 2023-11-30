package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.Diaria;

//import java.time.LocalDateTime;

public class DiariaDto {
	
	private Long id;
	private boolean completou;
	private String texto;
	//private LocalDateTime dataCriacao;
	
	public DiariaDto(Diaria diaria) {
		this.id = diaria.getId();
		this.texto = diaria.getTexto();
		this.completou = diaria.getCompletou();
	}
	
	public static List<DiariaDto> converter(List<Diaria> diarias) {
		// TODO Auto-generated method stub
		return diarias.stream().map(DiariaDto::new).collect(Collectors.toList());
	}
	
	
	public Long getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}
	
	public boolean getCompletou() {
		return completou;
	}
	
}
