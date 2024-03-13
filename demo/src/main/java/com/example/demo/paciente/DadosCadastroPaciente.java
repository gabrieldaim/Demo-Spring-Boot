package com.example.demo.paciente;
import com.example.demo.endereco.DadosEndereco;

import jakarta.validation.*;
import jakarta.validation.constraints.*;

public record DadosCadastroPaciente(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") String cpf,
        @NotNull @Valid DadosEndereco endereco
) {
}

