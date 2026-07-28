package com.gabriel.projeto_van.dto.veiculo;

import jakarta.validation.constraints.NotBlank;

public record VeiculoCreateDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Capacidade é obrigatório")
        int capacidade) {
}
