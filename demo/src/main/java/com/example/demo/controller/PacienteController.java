package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.paciente.DadosCadastroPaciente;
import com.example.demo.paciente.DadosListagemPaciente;
import com.example.demo.paciente.Paciente;
import com.example.demo.paciente.PacienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }
    
	@GetMapping	
	public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemPaciente::new);
	}
}
