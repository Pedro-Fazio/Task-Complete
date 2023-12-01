package com.crudGame.TaskComplete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
	
	Usuario findByNome(String nome);
}
