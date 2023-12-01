package com.crudGame.TaskComplete.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;

public class DetalhesUsuarioDto {
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String dinheiro;
	private int nivel;
	private int xp;
	
	public DetalhesUsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.email = usuario.getEmail();
		this.dinheiro = usuario.getDinheiro();
		this.nivel = usuario.getNivel();
		this.xp = usuario.getXp();
	}
	
	
	public static List<UsuarioDto> converter(List<Usuario> usuarios) {
		// TODO Auto-generated method stub
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
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
}
