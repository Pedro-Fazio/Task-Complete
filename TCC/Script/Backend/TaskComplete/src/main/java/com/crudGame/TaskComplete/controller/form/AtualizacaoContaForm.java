package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.ContaRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoContaForm {

	private String texto;
	
	private String dia;
	
	private String valor;

	private boolean reminder;

	private String usuarioEmail;
	
	public Conta atualizar (Long id, ContaRepository contaRepository, UsuarioLogadoRepository usuarioLogadoRepository, UsuarioRepository usuarioRepository) {
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		
		Conta conta = contaRepository.getReferenceById(id);
		conta.setTexto(texto);
		conta.setReminder(reminder);
		conta.setDia(dia);
		conta.setValor(valor);
		conta.setUsuario(usuario);
		
		return conta;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean getReminder() {
		return reminder;
	}

	public void setReminder(boolean reminder) {
		this.reminder = reminder;
	}

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
