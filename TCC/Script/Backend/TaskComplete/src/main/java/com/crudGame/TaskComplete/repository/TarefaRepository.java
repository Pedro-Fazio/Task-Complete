package com.crudGame.TaskComplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	public List<Tarefa> findByUsuario_Nome(String nomeUsuario);
}
