package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.Conta;

//import java.time.LocalDateTime;

public class ContaDto {
	
	private Long id;
	private String dia;
	private boolean reminder;
	private String texto;
	private String valor;
	//private LocalDateTime dataCriacao;
	
	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.dia = conta.getDia();
		this.reminder = conta.getReminder();
		this.texto = conta.getTexto();
		this.valor = conta.getValor();
	}
	
	public static List<ContaDto> converter(List<Conta> contas) {
		// TODO Auto-generated method stub
		return contas.stream().map(ContaDto::new).collect(Collectors.toList());
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
	
	public String getValor() {
		return valor;
	}
	
}
