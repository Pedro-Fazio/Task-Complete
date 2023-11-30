package com.crudGame.TaskComplete.controller.form;

import java.io.Console;

import com.crudGame.TaskComplete.modelo.Habito;
import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class HabitoForm {

	private Long contador;

	private String texto;

	private String usuarioEmail;
	
	public Habito converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.findByEmail(usuarioEmail);
		return new Habito(texto, contador, usuario);
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Long getContador() {
		return contador;
	}
	
	public void setContador(Long contador) {
		this.contador = contador;
	}
	
	public String getUsuarioId() {
		return usuarioEmail;
	}
	
	public void setUsuarioId(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
