package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Tarefa;

public class DetalhesTarefaDto {
	private Long id;
	private String texto;
	private String dia;
	private boolean reminder;
	private String usuarioEmail;
	
	public DetalhesTarefaDto(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.texto = tarefa.getTexto();
		this.dia = tarefa.getDia();
		this.reminder = tarefa.getReminder();
		this.usuarioEmail = tarefa.getUsuario().getEmail();
	}
	
	
	public static List<InventarioItemDto> converter(List<InventarioItem> inventarios) {
		// TODO Auto-generated method stub
		return inventarios.stream().map(InventarioItemDto::new).collect(Collectors.toList());
	}	
	
	
	public Long getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public String getDia() {
		return dia;
	}
	
	public boolean getReminder() {
		return reminder;
	}
	
	public String usuarioEmail() {
		return usuarioEmail;
	}
}
