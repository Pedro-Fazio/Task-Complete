package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.LojaItem;

public class DetalhesLojaItemDto {
	private Long id;
	private String nome;
	private String preco;
	private String imagem;
	private String usuarioEmail;
	
	public DetalhesLojaItemDto(LojaItem lojaItem) {
		this.id = lojaItem.getId();
		this.nome = lojaItem.getNome();
		this.preco = lojaItem.getPreco();
		this.imagem = lojaItem.getImagem();
		this.usuarioEmail = lojaItem.getUsuario().getEmail();
		
		//System.out.println(lojaItem.getUsuario().getEmail());
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
	
	public String usuarioEmail() {
		return usuarioEmail;
	}
}
