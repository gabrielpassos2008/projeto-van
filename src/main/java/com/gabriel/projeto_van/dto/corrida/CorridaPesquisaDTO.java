package com.gabriel.projeto_van.dto.corrida;

import jakarta.validation.constraints.NotBlank;

public record CorridaPesquisaDTO(
        @NotBlank(message = "Informe um nome para pesquisar")
        String pesquisa
) {
}
