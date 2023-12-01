package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.Diaria;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.DiariaRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoDiariaForm {

	private String texto;

	private boolean completou;

	private String usuarioEmail;
	
	public Diaria atualizar (Long id, DiariaRepository diariaRepository, UsuarioLogadoRepository usuarioLogadoRepository, UsuarioRepository usuarioRepository) {
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
		Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		
		Diaria diaria = diariaRepository.getReferenceById(id);
		diaria.setTexto(texto);
		diaria.setCompletou(completou);
		diaria.setUsuario(usuario);
		
		return diaria;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean getCompletou() {
		return completou;
	}

	public void setCompletou(boolean completou) {
		this.completou = completou;
	}

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
}
