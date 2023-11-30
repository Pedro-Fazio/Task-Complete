package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.Habito;
import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Tarefa;

public class DetalhesHabitoDto {
	private Long id;
	private String texto;
	private Long contador;
	private String usuarioEmail;
	
	public DetalhesHabitoDto(Habito habito) {
		this.id = habito.getId();
		this.texto = habito.getTexto();
		this.contador = habito.getContador();
		this.usuarioEmail = habito.getUsuario().getEmail();
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
	
	public String usuarioEmail() {
		return usuarioEmail;
	}
}
