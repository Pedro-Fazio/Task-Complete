package com.crudGame.TaskComplete.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crudGame.TaskComplete.controller.dto.DetalhesInventarioItemDto;
import com.crudGame.TaskComplete.controller.dto.InventarioItemDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoInventarioItemForm;
import com.crudGame.TaskComplete.controller.form.InventarioItemForm;
import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.InventarioItemRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
	
	@Autowired
	private InventarioItemRepository inventarioItemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioLogadoRepository usuarioLogadoRepository;
	
	@GetMapping
	public List<InventarioItemDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			//List<InventarioItem> inventarioItens = inventarioItemRepository.findAll();
			UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
			Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
			List<InventarioItem> inventarioItens = inventarioItemRepository.findByUsuarioId(usuario.getId());

			return InventarioItemDto.converter(inventarioItens);
		} else {
			List<InventarioItem> inventarioItens = inventarioItemRepository.findByUsuario_Nome(nomeUsuario);
			return InventarioItemDto.converter(inventarioItens);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<InventarioItemDto> cadastrar(@RequestBody @Valid InventarioItemForm inventarioItemForm, UriComponentsBuilder uriBuilder) {
		InventarioItem inventarioItem = inventarioItemForm.converter(usuarioRepository, usuarioLogadoRepository);
		
		System.out.println("testeInventario" + inventarioItem);
		
		inventarioItemRepository.save(inventarioItem);
		
		URI uri = uriBuilder.path("/loja/{id}").buildAndExpand(inventarioItem.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new InventarioItemDto(inventarioItem));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesInventarioItemDto> detalhar(@PathVariable Long id) {

		System.out.println("detalhar: " + id);
		
		Optional<InventarioItem> inventarioItem = inventarioItemRepository.findById(id);
		System.out.println("detalharMais: " + inventarioItem);
		
		if(inventarioItem.isPresent()) {
			return ResponseEntity.ok(new DetalhesInventarioItemDto(inventarioItem.get()));			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<InventarioItemDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoInventarioItemForm inventarioItemForm) {
		Optional<InventarioItem> optional = inventarioItemRepository.findById(id);
		
		System.out.println("optional: " + optional);
		
		if(optional.isPresent()) {
			InventarioItem inventarioItem = inventarioItemForm.atualizar(id, inventarioItemRepository, usuarioLogadoRepository, usuarioRepository);
			return ResponseEntity.ok(new InventarioItemDto(inventarioItem));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
