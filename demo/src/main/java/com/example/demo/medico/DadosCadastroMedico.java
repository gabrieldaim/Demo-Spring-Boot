package com.example.demo.medico;

import com.example.demo.endereco.DadosEndereco;

import jakarta.validation.*;
import jakarta.validation.constraints.*;


public record DadosCadastroMedico(
		@NotBlank
		String nome,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String telefone,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		@NotNull
		Especialidade especialidade,
		@NotNull
		@Valid
		DadosEndereco endereco) {

}
