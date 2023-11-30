package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

//import java.time.LocalDateTime;

import com.crudGame.TaskComplete.modelo.Tarefa;

public class TarefaDto {
	
	private Long id;
	private String dia;
	private boolean reminder;
	private String texto;
	//private LocalDateTime dataCriacao;
	
	public TarefaDto(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.dia = tarefa.getDia();
		this.reminder = tarefa.getReminder();
		this.texto = tarefa.getTexto();
	}
	
	public static List<TarefaDto> converter(List<Tarefa> tarefas) {
		// TODO Auto-generated method stub
		return tarefas.stream().map(TarefaDto::new).collect(Collectors.toList());
	}
	
	
	public Long getId() {
		return id;
	}

	public String getDia() {
		return dia;
	}

	public boolean getReminder() {
		return reminder;
	}

	public String getTexto() {
		return texto;
	}
	
}
