package com.crudGame.TaskComplete.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioLogado {

	//@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
 	private Long id;
	private String nome;
	private String email;
	private String senha;
    private String dinheiro;
    private int nivel;
    private int xp;
	@ManyToOne
	private Usuario usuario;
	private boolean isDarkMode;
	private boolean isBigSize;
    
    public UsuarioLogado() {
		
	}
	
	public UsuarioLogado(String nome, String email, String senha, String dinheiro, int nivel, int xp,
			Usuario usuario, boolean isDarkMode, boolean isBigSize) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.dinheiro = dinheiro;
		this.nivel = nivel;
		this.xp = xp;
		this.usuario = usuario;
		this.isDarkMode = isDarkMode;
		this.isBigSize = isBigSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioLogado other = (UsuarioLogado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
