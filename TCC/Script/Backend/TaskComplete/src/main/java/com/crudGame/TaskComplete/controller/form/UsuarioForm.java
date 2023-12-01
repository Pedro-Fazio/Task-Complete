package com.crudGame.TaskComplete.controller.form;

import java.io.Console;

import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UsuarioForm {

	private String nome;
	private String email;
	private String senha;
	private String dinheiro;
	private int nivel;
	private int xp;
	
	public Usuario converter(UsuarioRepository usuarioRepository) {
		//Usuario usuario = usuarioRepository.findByEmail(usuarioEmail);
		return new Usuario(nome, email, senha, dinheiro, nivel, xp);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}
