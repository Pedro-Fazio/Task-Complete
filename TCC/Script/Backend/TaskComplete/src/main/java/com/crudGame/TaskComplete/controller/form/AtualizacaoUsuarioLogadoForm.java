package com.crudGame.TaskComplete.controller.form;

import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoUsuarioLogadoForm {

	private String nome;
	private String email;
	private String senha;
	private String dinheiro;
	private int nivel;
	private int xp;
	private String usuarioEmail;
	private boolean isDarkMode;
	private boolean isBigSize;
	
	public UsuarioLogado atualizar (Long id, UsuarioLogadoRepository usuarioLogadoRepository, UsuarioRepository usuarioRepository) {
		UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(id);
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		usuarioLogado.setNome(nome);
		usuarioLogado.setEmail(email);
		usuarioLogado.setSenha(senha);
		usuarioLogado.setDinheiro(dinheiro);
		usuarioLogado.setNivel(nivel);
		usuarioLogado.setXp(xp);
		usuarioLogado.setUsuario(usuario);
		usuarioLogado.setIsDarkMode(isDarkMode);
		usuarioLogado.setIsBigSize(isBigSize);
		
		return usuarioLogado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(String dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
	
	public boolean getIsDarkMode() {
		return isDarkMode;
	}

	public void setIsDarkMode(boolean isDarkMode) {
		this.isDarkMode = isDarkMode;
	}
	
	public boolean getIsBigSize() {
		return isBigSize;
	}

	public void setIsBigSize(boolean isBigSize) {
		this.isBigSize = isBigSize; 
	}
}
