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
import com.crudGame.TaskComplete.controller.form.AtualizacaoContaForm;
import com.crudGame.TaskComplete.controller.form.ContaForm;
import com.crudGame.TaskComplete.modelo.Conta;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.ContaRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
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
	
	@Autowired
	private UsuarioLogadoRepository usuarioLogadoRepository;
	
	@GetMapping
	public List<ContaDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			//List<Conta> contas = contaRepository.findAll();
			UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
			Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
			List<Conta> contas = contaRepository.findByUsuarioId(usuario.getId());
			
			return ContaDto.converter(contas);
		} else {
			List<Conta> contas = contaRepository.findByUsuario_Nome(nomeUsuario);
			return ContaDto.converter(contas);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm contaForm, UriComponentsBuilder uriBuilder) {
		Conta conta = contaForm.converter(usuarioRepository, usuarioLogadoRepository);
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
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoContaForm contaForm) {
		Optional<Conta> optional = contaRepository.findById(id);
		if(optional.isPresent()) {
			Conta conta = contaForm.atualizar(id, contaRepository, usuarioLogadoRepository, usuarioRepository);
			return ResponseEntity.ok(new ContaDto(conta));	
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
}
