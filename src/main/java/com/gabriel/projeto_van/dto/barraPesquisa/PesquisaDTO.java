package com.gabriel.projeto_van.dto.barraPesquisa;

import jakarta.validation.constraints.NotBlank;

public record PesquisaDTO(
        @NotBlank(message = "Informe um nome para pesquisar")
        String pesquisa
) {
}
