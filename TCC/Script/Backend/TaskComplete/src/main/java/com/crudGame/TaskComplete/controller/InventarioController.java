package com.crudGame.TaskComplete.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crudGame.TaskComplete.controller.dto.DetalhesInventarioItemDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesLojaItemDto;
import com.crudGame.TaskComplete.controller.dto.InventarioItemDto;
import com.crudGame.TaskComplete.controller.dto.LojaItemDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoInventarioItemForm;
import com.crudGame.TaskComplete.controller.form.AtualizacaoLojaItemForm;
import com.crudGame.TaskComplete.controller.form.InventarioItemForm;
import com.crudGame.TaskComplete.controller.form.LojaItemForm;
import com.crudGame.TaskComplete.modelo.InventarioItem;
import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.repository.InventarioItemRepository;
import com.crudGame.TaskComplete.repository.LojaItemRepository;
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
	
	@GetMapping
	public List<InventarioItemDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			List<InventarioItem> inventarioItens = inventarioItemRepository.findAll();
			System.out.println(inventarioItens);
			return InventarioItemDto.converter(inventarioItens);
		} else {
			List<InventarioItem> inventarioItens = inventarioItemRepository.findByUsuario_Nome(nomeUsuario);
			return InventarioItemDto.converter(inventarioItens);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<InventarioItemDto> cadastrar(@RequestBody @Valid InventarioItemForm inventarioItemForm, UriComponentsBuilder uriBuilder) {
		InventarioItem inventarioItem = inventarioItemForm.converter(usuarioRepository);
		
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
			InventarioItem inventarioItem = inventarioItemForm.atualizar(id, inventarioItemRepository);
			return ResponseEntity.ok(new InventarioItemDto(inventarioItem));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*@Autowired
	private LojaItemRepository lojaItemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<LojaItemDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			List<LojaItem> lojaItens = lojaItemRepository.findAll();
			return LojaItemDto.converter(lojaItens);
		} else {
			List<LojaItem> lojaItens = lojaItemRepository.findByUsuario_Nome(nomeUsuario);
			return LojaItemDto.converter(lojaItens);
		}
				
		//LojaItem lojaItem = new LojaItem("Cabelo 1", "6,90", "https://i.imgur.com/sHllgOM.png", new Usuario("Pedroo", "darknighmare1@gmail.com", "teste", "917", 4, 40));
		//return LojaItemDto.converter(Arrays.asList(lojaItem, lojaItem, lojaItem));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LojaItemDto> cadastrar(@RequestBody @Valid LojaItemForm lojaItemForm, UriComponentsBuilder uriBuilder) {
		LojaItem lojaItem = lojaItemForm.converter(usuarioRepository);
		lojaItemRepository.save(lojaItem);
		
		URI uri = uriBuilder.path("/loja/{id}").buildAndExpand(lojaItem.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new LojaItemDto(lojaItem));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesLojaItemDto> detalhar(@PathVariable Long id) {
		//LojaItem lojaItem = lojaItemRepository.getReferenceById(id);
		Optional<LojaItem> lojaItem = lojaItemRepository.findById(id);
		if(lojaItem.isPresent()) {
			return ResponseEntity.ok(new DetalhesLojaItemDto(lojaItem.get()));			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LojaItemDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoLojaItemForm lojaItemForm) {
		Optional<LojaItem> optional = lojaItemRepository.findById(id);
		if(optional.isPresent()) {
			LojaItem lojaItem = lojaItemForm.atualizar(id, lojaItemRepository);
			return ResponseEntity.ok(new LojaItemDto(lojaItem));		
		} else {
			return ResponseEntity.notFound().build();
		}
		
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<LojaItem> optional = lojaItemRepository.findById(id);
		
		if(optional.isPresent()) {
			lojaItemRepository.deleteById(id);
			return ResponseEntity.ok().build();		
		} else {
			return ResponseEntity.notFound().build();
		}
	}	*/
}
