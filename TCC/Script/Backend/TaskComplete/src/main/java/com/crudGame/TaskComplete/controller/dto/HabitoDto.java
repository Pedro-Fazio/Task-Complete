package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.Habito;

//import java.time.LocalDateTime;

public class HabitoDto {
	
	private Long id;
	private Long contador;
	private String texto;
	//private LocalDateTime dataCriacao;
	
	public HabitoDto(Habito habito) {
		this.id = habito.getId();
		this.texto = habito.getTexto();
		this.contador = habito.getContador();
	}
	
	public static List<HabitoDto> converter(List<Habito> habitos) {
		// TODO Auto-generated method stub
		return habitos.stream().map(HabitoDto::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}
	
	public Long getContador() {
		return contador;
	}
	
}
