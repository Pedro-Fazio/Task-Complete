package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class InventarioItemForm {

	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String preco;
	@NotNull @NotEmpty
	private String imagem;

	private boolean equipado; 

	private String usuarioEmail;
	
	public InventarioItem converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.findByEmail(usuarioEmail);
		return new InventarioItem(nome, preco, imagem, equipado, usuario);
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
	
	public boolean getEquipado() {
		return equipado;
	}
	
	public void setEquipado(boolean equipado) {
		this.equipado = equipado;
	}
	
	public String getUsuarioId() {
		return usuarioEmail;
	}
	
	public void setUsuarioId(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
