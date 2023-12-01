package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.InventarioItemRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoInventarioItemForm {

	private String nome;

	private String preco;

	private String imagem;

	private boolean equipado;

	private String usuarioEmail;
	
	public InventarioItem atualizar (Long id, InventarioItemRepository inventarioItemRepository, UsuarioLogadoRepository usuarioLogadoRepository, UsuarioRepository usuarioRepository) {
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		
		InventarioItem inventarioItem = inventarioItemRepository.getReferenceById(id);
		inventarioItem.setNome(nome);
		inventarioItem.setImagem(imagem);
		inventarioItem.setPreco(preco);
		inventarioItem.setEquipado(equipado);
		inventarioItem.setUsuario(usuario);
		
		return inventarioItem;
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
