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

import com.crudGame.TaskComplete.controller.dto.ContaDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesContaDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesLojaItemDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesTarefaDto;
import com.crudGame.TaskComplete.controller.dto.LojaItemDto;
import com.crudGame.TaskComplete.controller.dto.TarefaDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoLojaItemForm;
import com.crudGame.TaskComplete.controller.form.ContaForm;
import com.crudGame.TaskComplete.controller.form.LojaItemForm;
import com.crudGame.TaskComplete.controller.form.TarefaForm;
import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.repository.ContaRepository;
import com.crudGame.TaskComplete.repository.LojaItemRepository;
import com.crudGame.TaskComplete.repository.TarefaRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<ContaDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			List<Conta> contas = contaRepository.findAll();
			return ContaDto.converter(contas);
		} else {
			List<Conta> contas = contaRepository.findByUsuario_Nome(nomeUsuario);
			return ContaDto.converter(contas);
		}
				
		//LojaItem lojaItem = new LojaItem("Cabelo 1", "6,90", "https://i.imgur.com/sHllgOM.png", new Usuario("Pedroo", "darknighmare1@gmail.com", "teste", "917", 4, 40));
		//return LojaItemDto.converter(Arrays.asList(lojaItem, lojaItem, lojaItem));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm contaForm, UriComponentsBuilder uriBuilder) {
		Conta conta = contaForm.converter(usuarioRepository);
		contaRepository.save(conta);
		
		URI uri = uriBuilder.path("/loja/{id}").buildAndExpand(conta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesContaDto> detalhar(@PathVariable Long id) {
		//LojaItem lojaItem = lojaItemRepository.getReferenceById(id);
		Optional<Conta> conta = contaRepository.findById(id);
		if(conta.isPresent()) {
			return ResponseEntity.ok(new DetalhesContaDto(conta.get()));			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Conta> optional = contaRepository.findById(id);
		
		if(optional.isPresent()) {
			contaRepository.deleteById(id);
			return ResponseEntity.ok().build();		
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	@Autowired
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
	}*/
}
