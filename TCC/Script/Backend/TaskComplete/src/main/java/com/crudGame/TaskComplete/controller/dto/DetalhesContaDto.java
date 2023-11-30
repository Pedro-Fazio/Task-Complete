package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Tarefa;

public class DetalhesContaDto {
	private Long id;
	private String texto;
	private String dia;
	private boolean reminder;
	private String valor;
	private String usuarioEmail;
	
	public DetalhesContaDto(Conta conta) {
		this.id = conta.getId();
		this.texto = conta.getTexto();
		this.dia = conta.getDia();
		this.reminder = conta.getReminder();
		this.valor = conta.getValor();
		this.usuarioEmail = conta.getUsuario().getEmail();
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
	
	public String getValor() {
		return valor;
	}
	
	public String usuarioEmail() {
		return usuarioEmail;
	}	
}
