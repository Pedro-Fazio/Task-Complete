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

import com.crudGame.TaskComplete.controller.dto.DetalhesDiariaDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesLojaItemDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesTarefaDto;
import com.crudGame.TaskComplete.controller.dto.DiariaDto;
import com.crudGame.TaskComplete.controller.dto.LojaItemDto;
import com.crudGame.TaskComplete.controller.dto.TarefaDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoDiariaForm;
import com.crudGame.TaskComplete.controller.form.AtualizacaoLojaItemForm;
import com.crudGame.TaskComplete.controller.form.AtualizacaoTarefaForm;
import com.crudGame.TaskComplete.controller.form.DiariaForm;
import com.crudGame.TaskComplete.controller.form.LojaItemForm;
import com.crudGame.TaskComplete.controller.form.TarefaForm;
import com.crudGame.TaskComplete.modelo.Diaria;
import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.DiariaRepository;
import com.crudGame.TaskComplete.repository.LojaItemRepository;
import com.crudGame.TaskComplete.repository.TarefaRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/diarias")
public class DiariaController {
	
	@Autowired
	private DiariaRepository diariaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioLogadoRepository usuarioLogadoRepository;
	
	@GetMapping
	public List<DiariaDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			//List<Diaria> diarias = diariaRepository.findAll();
			UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
			Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
			List<Diaria> diarias = diariaRepository.findByUsuarioId(usuario.getId());
			
			return DiariaDto.converter(diarias);
		} else {
			List<Diaria> diarias = diariaRepository.findByUsuario_Nome(nomeUsuario);
			return DiariaDto.converter(diarias);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DiariaDto> cadastrar(@RequestBody @Valid DiariaForm diariaForm, UriComponentsBuilder uriBuilder) {
		Diaria diaria = diariaForm.converter(usuarioRepository, usuarioLogadoRepository);
		diariaRepository.save(diaria);
		
		URI uri = uriBuilder.path("/loja/{id}").buildAndExpand(diaria.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DiariaDto(diaria));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDiariaDto> detalhar(@PathVariable Long id) {
		Optional<Diaria> diaria = diariaRepository.findById(id);
		if(diaria.isPresent()) {
			return ResponseEntity.ok(new DetalhesDiariaDto(diaria.get()));			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DiariaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoDiariaForm diariaForm) {
		Optional<Diaria> optional = diariaRepository.findById(id);
		if(optional.isPresent()) {
			Diaria diaria = diariaForm.atualizar(id, diariaRepository, usuarioLogadoRepository, usuarioRepository);
			return ResponseEntity.ok(new DiariaDto(diaria));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Diaria> optional = diariaRepository.findById(id);
		
		if(optional.isPresent()) {
			diariaRepository.deleteById(id);
			return ResponseEntity.ok().build();		
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
