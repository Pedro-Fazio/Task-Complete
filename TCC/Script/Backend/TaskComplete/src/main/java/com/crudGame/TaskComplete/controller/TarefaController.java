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

import com.crudGame.TaskComplete.controller.dto.DetalhesTarefaDto;
import com.crudGame.TaskComplete.controller.dto.TarefaDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoTarefaForm;
import com.crudGame.TaskComplete.controller.form.TarefaForm;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.TarefaRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioLogadoRepository usuarioLogadoRepository;
	
	@GetMapping
	public List<TarefaDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			//List<Tarefa> tarefas = tarefaRepository.findAll();
			UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
			Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
			List<Tarefa> tarefas = tarefaRepository.findByUsuarioId(usuario.getId());
			
			return TarefaDto.converter(tarefas);
		} else {
			List<Tarefa> tarefas = tarefaRepository.findByUsuario_Nome(nomeUsuario);
			return TarefaDto.converter(tarefas);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TarefaDto> cadastrar(@RequestBody @Valid TarefaForm tarefaForm, UriComponentsBuilder uriBuilder) {
		Tarefa tarefa = tarefaForm.converter(usuarioRepository, usuarioLogadoRepository);
		tarefaRepository.save(tarefa);
		
		URI uri = uriBuilder.path("/loja/{id}").buildAndExpand(tarefa.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TarefaDto(tarefa));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTarefaDto> detalhar(@PathVariable Long id) {
		//LojaItem lojaItem = lojaItemRepository.getReferenceById(id);
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		if(tarefa.isPresent()) {
			return ResponseEntity.ok(new DetalhesTarefaDto(tarefa.get()));			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TarefaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTarefaForm tarefaForm) {
		Optional<Tarefa> optional = tarefaRepository.findById(id);
		if(optional.isPresent()) {
			Tarefa tarefa = tarefaForm.atualizar(id, tarefaRepository, usuarioLogadoRepository, usuarioRepository);
			return ResponseEntity.ok(new TarefaDto(tarefa));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Tarefa> optional = tarefaRepository.findById(id);
		
		if(optional.isPresent()) {
			tarefaRepository.deleteById(id);
			return ResponseEntity.ok().build();		
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
