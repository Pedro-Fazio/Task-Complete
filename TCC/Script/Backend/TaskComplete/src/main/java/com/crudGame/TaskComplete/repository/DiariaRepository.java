package com.crudGame.TaskComplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.Diaria;
import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Tarefa;

public interface DiariaRepository extends JpaRepository<Diaria, Long> {
	public List<Diaria> findByUsuario_Nome(String nomeUsuario);
	
	List<Diaria> findByUsuarioId(Long usuarioId);
}
