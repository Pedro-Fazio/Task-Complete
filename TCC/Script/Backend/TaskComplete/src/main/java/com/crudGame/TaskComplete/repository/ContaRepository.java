package com.crudGame.TaskComplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.Tarefa;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	public List<Conta> findByUsuario_Nome(String nomeUsuario);
}
