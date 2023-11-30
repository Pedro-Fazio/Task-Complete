package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.InventarioItem;

//import java.time.LocalDateTime;

public class InventarioItemDto {
	
	private Long id;
	private String nome;
	private String preco;
	private String imagem;
	private boolean equipado;
	//private LocalDateTime dataCriacao;
	
	public InventarioItemDto(InventarioItem inventarioItem) {
		this.id = inventarioItem.getId();
		this.nome = inventarioItem.getNome();
		this.preco = inventarioItem.getPreco();
		this.imagem = inventarioItem.getImagem();
		this.equipado = inventarioItem.getEquipado();
	}
	
	public static List<InventarioItemDto> converter(List<InventarioItem> inventarios) {
		// TODO Auto-generated method stub
		return inventarios.stream().map(InventarioItemDto::new).collect(Collectors.toList());
	}	
	
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPreco() {
		return preco;
	}

	public String getImagem() {
		return imagem;
	}
	
	public boolean getEquipado() {
		return equipado;
	}
	
}
