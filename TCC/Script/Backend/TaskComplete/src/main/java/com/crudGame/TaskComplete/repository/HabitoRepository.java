package com.crudGame.TaskComplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.Habito;
import com.crudGame.TaskComplete.modelo.Tarefa;

public interface HabitoRepository extends JpaRepository<Habito, Long> {
	public List<Habito> findByUsuario_Nome(String nomeUsuario);
}
