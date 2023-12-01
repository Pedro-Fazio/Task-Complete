package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.TarefaRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoTarefaForm {

	private String texto;

	private String dia;

	private boolean reminder;

	private String usuarioEmail;
	
	public Tarefa atualizar (Long id, TarefaRepository tarefaRepository, UsuarioLogadoRepository usuarioLogadoRepository, UsuarioRepository usuarioRepository) {	
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		
		Tarefa tarefa = tarefaRepository.getReferenceById(id);
		tarefa.setTexto(texto);
		tarefa.setDia(dia);
		tarefa.setReminder(reminder);
		tarefa.setUsuario(usuario);
		
		return tarefa;
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
