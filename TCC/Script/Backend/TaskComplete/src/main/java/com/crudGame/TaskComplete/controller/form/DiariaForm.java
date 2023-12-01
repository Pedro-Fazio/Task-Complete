package com.crudGame.TaskComplete.controller.form;

import java.io.Console;

import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.Diaria;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DiariaForm {

	private String texto;

	private boolean completou;

	private String usuarioEmail;
	
	public Diaria converter(UsuarioRepository usuarioRepository, UsuarioLogadoRepository usuarioLogadoRepository) {
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		
		return new Diaria(texto, completou, usuario);
	}
	

	public boolean getCompletou() {
		return completou;
	}
	
	public void setCompletou(boolean completou) {
		this.completou = completou;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getUsuarioId() {
		return usuarioEmail;
	}
	
	public void setUsuarioId(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
