package com.crudGame.TaskComplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.LojaItem;

public interface LojaItemRepository extends JpaRepository<LojaItem, Long> {
	public List<LojaItem> findByUsuario_Nome(String nomeUsuario);
}
