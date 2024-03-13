package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.demo.medico.DadosAtualizacaoMedico;
import com.example.demo.medico.DadosCadastroMedico;
import com.example.demo.medico.DadosListagemMedico;
import com.example.demo.medico.Medico;
import com.example.demo.medico.MedicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		repository.save(new Medico(dados));
	}
	
	@GetMapping	
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10,sort = {"crm"}) Pageable paginacao){
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();
	}
}
