package com.crudGame.TaskComplete.controller.form;

import java.io.Console;

import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ContaForm {


	private String dia;

	private boolean reminder;

	private String texto;
	
	private String valor;

	private String usuarioEmail;
	
	public Conta converter(UsuarioRepository usuarioRepository, UsuarioLogadoRepository usuarioLogadoRepository) {
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		
		return new Conta(texto, dia, valor, reminder, usuario);
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
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getUsuarioId() {
		return usuarioEmail;
	}
	
	public void setUsuarioId(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
