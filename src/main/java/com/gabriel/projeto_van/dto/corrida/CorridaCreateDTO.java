package com.gabriel.projeto_van.dto.corrida;

import jakarta.validation.constraints.NotBlank;

public record CorridaCreateDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "turno é obrigatório")
        String turno) {
}
