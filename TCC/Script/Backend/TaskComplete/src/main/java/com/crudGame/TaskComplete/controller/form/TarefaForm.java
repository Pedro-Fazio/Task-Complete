package com.crudGame.TaskComplete.controller.form;

import java.io.Console;

import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TarefaForm {


	private String dia;

	private boolean reminder;

	private String texto;

	private String usuarioEmail;
	
	public Tarefa converter(UsuarioRepository usuarioRepository) {
		System.out.println("texxxt: " + texto);
		Usuario usuario = usuarioRepository.findByEmail(usuarioEmail);
		return new Tarefa(texto, dia, reminder, usuario);
	}
	
	public String getDia() {
		return dia;
	}
	
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	public boolean getReminder() {
		return reminder;
	}
	
	public void setReminder(boolean reminder) {
		this.reminder = reminder;
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
