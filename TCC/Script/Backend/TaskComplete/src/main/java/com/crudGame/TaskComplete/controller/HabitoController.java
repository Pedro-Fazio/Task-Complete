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

import com.crudGame.TaskComplete.controller.dto.DetalhesHabitoDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesLojaItemDto;
import com.crudGame.TaskComplete.controller.dto.DetalhesTarefaDto;
import com.crudGame.TaskComplete.controller.dto.DiariaDto;
import com.crudGame.TaskComplete.controller.dto.HabitoDto;
import com.crudGame.TaskComplete.controller.dto.LojaItemDto;
import com.crudGame.TaskComplete.controller.dto.TarefaDto;
import com.crudGame.TaskComplete.controller.form.AtualizacaoDiariaForm;
import com.crudGame.TaskComplete.controller.form.AtualizacaoHabitoForm;
import com.crudGame.TaskComplete.controller.form.AtualizacaoLojaItemForm;
import com.crudGame.TaskComplete.controller.form.HabitoForm;
import com.crudGame.TaskComplete.controller.form.LojaItemForm;
import com.crudGame.TaskComplete.controller.form.TarefaForm;
import com.crudGame.TaskComplete.modelo.Diaria;
import com.crudGame.TaskComplete.modelo.Habito;
import com.crudGame.TaskComplete.modelo.LojaItem;
import com.crudGame.TaskComplete.modelo.Tarefa;
import com.crudGame.TaskComplete.modelo.Usuario;
import com.crudGame.TaskComplete.modelo.UsuarioLogado;
import com.crudGame.TaskComplete.repository.HabitoRepository;
import com.crudGame.TaskComplete.repository.LojaItemRepository;
import com.crudGame.TaskComplete.repository.TarefaRepository;
import com.crudGame.TaskComplete.repository.UsuarioLogadoRepository;
import com.crudGame.TaskComplete.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/habitos")
public class HabitoController {
	
	@Autowired
	private HabitoRepository habitoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioLogadoRepository usuarioLogadoRepository;
	
	@GetMapping
	public List<HabitoDto> lista(String nomeUsuario) {
		if(nomeUsuario == null) {
			//List<Habito> habitos = habitoRepository.findAll();
			UsuarioLogado usuarioLogado = usuarioLogadoRepository.getReferenceById(Long.valueOf(1));
			Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
			List<Habito> habitos = habitoRepository.findByUsuarioId(usuario.getId());
			
			return HabitoDto.converter(habitos);
		} else {
			List<Habito> habitos = habitoRepository.findByUsuario_Nome(nomeUsuario);
			return HabitoDto.converter(habitos);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<HabitoDto> cadastrar(@RequestBody @Valid HabitoForm habitoForm, UriComponentsBuilder uriBuilder) {
		Habito habito = habitoForm.converter(usuarioRepository, usuarioLogadoRepository);
		habitoRepository.save(habito);
		
		URI uri = uriBuilder.path("/loja/{id}").buildAndExpand(habito.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new HabitoDto(habito));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesHabitoDto> detalhar(@PathVariable Long id) {
		Optional<Habito> habito = habitoRepository.findById(id);
		if(habito.isPresent()) {
			return ResponseEntity.ok(new DetalhesHabitoDto(habito.get()));			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<HabitoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoHabitoForm habitoForm) {
		Optional<Habito> optional = habitoRepository.findById(id);
		if(optional.isPresent()) {
			Habito habito = habitoForm.atualizar(id, habitoRepository, usuarioLogadoRepository, usuarioRepository);
			return ResponseEntity.ok(new HabitoDto(habito));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Habito> optional = habitoRepository.findById(id);
		
		if(optional.isPresent()) {
			habitoRepository.deleteById(id);
			return ResponseEntity.ok().build();		
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
