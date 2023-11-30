package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.Diaria;
import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Tarefa;

public class DetalhesDiariaDto {
	private Long id;
	private String texto;
	private boolean completou;
	private String usuarioEmail;
	
	public DetalhesDiariaDto(Diaria diaria) {
		this.id = diaria.getId();
		this.texto = diaria.getTexto();
		this.completou = diaria.getCompletou();
		this.usuarioEmail = diaria.getUsuario().getEmail();
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
	
	public String usuarioEmail() {
		return usuarioEmail;
	}
}
