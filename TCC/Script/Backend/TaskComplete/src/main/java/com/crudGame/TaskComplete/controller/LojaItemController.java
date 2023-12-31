package com.crudGame.TaskComplete.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.crudGame.TaskComplete.controller.dto.DetalhesLojaItemDto;
import com.crudGame.TaskComplete.controller.dto.LojaItemDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoLojaItemForm;
import com.crudGame.TaskComplete.controller.form.LojaItemForm;
import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.LojaItemRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/loja")
public class LojaItemController {
	
	@Autowired
	private LojaItemRepository lojaItemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioLogadoRepository usuarioLogadoRepository;
	
	@GetMapping
	public List<LojaItemDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			//List<LojaItem> lojaItens = lojaItemRepository.findAll();
			UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
			Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
			List<LojaItem> lojaItens = lojaItemRepository.findByUsuarioId(usuario.getId());
			
			return LojaItemDto.converter(lojaItens);
		} else {
			List<LojaItem> lojaItens = lojaItemRepository.findByUsuario_Nome(nomeUsuario);
			return LojaItemDto.converter(lojaItens);
		}
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
	}	
}
