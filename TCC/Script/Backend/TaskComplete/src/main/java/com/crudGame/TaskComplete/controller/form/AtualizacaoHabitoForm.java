package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.Habito;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.HabitoRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoHabitoForm {

	private String texto;

	private Long contador;

	private String usuarioEmail;
	
	public Habito atualizar (Long id, HabitoRepository habitoRepository, UsuarioLogadoRepository usuarioLogadoRepository, UsuarioRepository usuarioRepository) {		
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		
		Habito habito = habitoRepository.getReferenceById(id);
		habito.setTexto(texto);
		habito.setContador(contador);
		habito.setUsuario(usuario);
		
		return habito;
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

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
