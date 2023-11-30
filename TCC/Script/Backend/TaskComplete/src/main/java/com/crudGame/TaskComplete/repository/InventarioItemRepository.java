package com.crudGame.TaskComplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudGame.TaskComplete.modelo.InventarioItem;

public interface InventarioItemRepository extends JpaRepository<InventarioItem, Long> {
	public List<InventarioItem> findByUsuario_Nome(String nomeUsuario);
}
