package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LojaItemForm {

	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String preco;
	@NotNull @NotEmpty
	private String imagem;

	private String usuarioEmail;
	
	public LojaItem converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.findByEmail(usuarioEmail);
		return new LojaItem(nome, preco, imagem, usuario);
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
	public String getUsuarioId() {
		return usuarioEmail;
	}
	public void setUsuarioId(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
