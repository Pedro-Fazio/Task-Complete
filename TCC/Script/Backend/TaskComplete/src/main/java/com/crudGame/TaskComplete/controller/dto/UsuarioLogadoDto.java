package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

//import java.time.LocalDateTime;

import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;

public class UsuarioLogadoDto {
	
	private Long id;
	private String nome;
	private String senha;
	private String email;
	private String dinheiro;
	private int nivel;
	private int xp;
	private String usuarioEmail;
	
	public UsuarioLogadoDto(UsuarioLogado usuarioLogado) {
		this.id = usuarioLogado.getId();
		this.nome = usuarioLogado.getNome();
		this.senha = usuarioLogado.getSenha();
		this.email = usuarioLogado.getEmail();
		this.dinheiro = usuarioLogado.getDinheiro();
		this.nivel = usuarioLogado.getNivel();
		this.xp = usuarioLogado.getXp();
		this.usuarioEmail = usuarioLogado.getUsuario().getEmail();
	}
	
	public static List<UsuarioLogadoDto> converter(List<UsuarioLogado> usuariosLogados) {
		// TODO Auto-generated method stub
		return usuariosLogados.stream().map(UsuarioLogadoDto::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}

	public String getDinheiro() {
		return dinheiro;
	}

	public int getNivel() {
		return nivel;
	}

	public int getXp() {
		return xp;
	}
	
	public String usuarioEmail() {
		return usuarioEmail;
	}
}
