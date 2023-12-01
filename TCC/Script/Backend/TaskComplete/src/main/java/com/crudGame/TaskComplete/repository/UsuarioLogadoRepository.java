package com.crudGame.TaskComplete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;

public interface UsuarioLogadoRepository extends JpaRepository<UsuarioLogado, Long>{

	UsuarioLogado findByEmail(String email);
	
	UsuarioLogado findByNome(String nome);
}
