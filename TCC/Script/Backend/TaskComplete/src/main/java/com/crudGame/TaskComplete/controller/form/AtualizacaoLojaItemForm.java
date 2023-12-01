package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.repository.LojaItemRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoLojaItemForm {
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String preco;
	@NotNull @NotEmpty
	private String imagem;

	private String usuarioEmail;
	
	public LojaItem atualizar (Long id, LojaItemRepository lojaItemRepository) {
		LojaItem lojaItem = lojaItemRepository.getReferenceById(id);
		lojaItem.setNome(nome);
		lojaItem.setImagem(imagem);
		lojaItem.setPreco(preco);
		lojaItem.setUsuario(lojaItem.getUsuario());
		
		return lojaItem;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getUsuarioEmail() {
		return usuarioEmail;
	}
	
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
