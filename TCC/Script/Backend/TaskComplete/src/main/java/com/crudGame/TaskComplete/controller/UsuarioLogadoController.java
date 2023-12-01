package com.crudGame.TaskComplete.controller;

import java.net.URI;
import java.util.ArrayList;
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

import com.crudGame.TaskComplete.controller.dto.DetalhesUsuarioLogadoDto;
import com.crudGame.TaskComplete.controller.dto.UsuarioLogadoDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoUsuarioLogadoForm;
import com.crudGame.TaskComplete.controller.form.UsuarioLogadoForm;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarioLogado")
public class UsuarioLogadoController {
	
	
	@Autowired
	private UsuarioLogadoRepository usuarioLogadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<UsuarioLogadoDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			List<UsuarioLogado> usuariosLogados = usuarioLogadoRepository.findAll();
			return UsuarioLogadoDto.converter(usuariosLogados);
		} else {
			List<UsuarioLogado> usuariosLogados = new ArrayList<>();
			UsuarioLogado usuarioLogado = usuarioLogadoRepository.findByNome(nomeUsuario);
			usuariosLogados.add(usuarioLogado);
			return UsuarioLogadoDto.converter(usuariosLogados);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioLogadoDto> cadastrar(@RequestBody @Valid UsuarioLogadoForm usuarioLogadoForm, UriComponentsBuilder uriBuilder) {
		UsuarioLogado usuarioLogado = usuarioLogadoForm.converter(usuarioRepository);
		usuarioLogadoRepository.save(usuarioLogado);
		
		URI uri = uriBuilder.path("/loja/{id}").buildAndExpand(usuarioLogado.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioLogadoDto(usuarioLogado));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesUsuarioLogadoDto> detalhar(@PathVariable Long id) {
		Optional<UsuarioLogado> usuarioLogado = usuarioLogadoRepository.findById(id);
		if(usuarioLogado.isPresent()) {
			return ResponseEntity.ok(new DetalhesUsuarioLogadoDto(usuarioLogado.get()));			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioLogadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioLogadoForm usuarioLogadoForm) {
		Optional<UsuarioLogado> optional = usuarioLogadoRepository.findById(id);
		if(optional.isPresent()) {
			UsuarioLogado usuarioLogado = usuarioLogadoForm.atualizar(id, usuarioLogadoRepository, usuarioRepository);
			return ResponseEntity.ok(new UsuarioLogadoDto(usuarioLogado));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<UsuarioLogado> optional = usuarioLogadoRepository.findById(id);
		
		if(optional.isPresent()) {
			usuarioLogadoRepository.deleteById(id);
			return ResponseEntity.ok().build();		
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
