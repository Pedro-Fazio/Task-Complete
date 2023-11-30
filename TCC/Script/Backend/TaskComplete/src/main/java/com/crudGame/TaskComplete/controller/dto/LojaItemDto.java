package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

//import java.time.LocalDateTime;

import com.crudGame.TaskComplete.modelo.LojaItem;

public class LojaItemDto {
	
	private Long id;
	private String nome;
	private String preco;
	private String imagem;
	//private LocalDateTime dataCriacao;
	
	public LojaItemDto(LojaItem lojaItem) {
		this.id = lojaItem.getId();
		this.nome = lojaItem.getNome();
		this.preco = lojaItem.getPreco();
		this.imagem = lojaItem.getImagem();
	}
	
	public static List<LojaItemDto> converter(List<LojaItem> lojaItens) {
		// TODO Auto-generated method stub
		return lojaItens.stream().map(LojaItemDto::new).collect(Collectors.toList());
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
	
}
