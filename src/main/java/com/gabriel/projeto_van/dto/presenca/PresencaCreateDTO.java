package com.gabriel.projeto_van.dto.presenca;

import jakarta.validation.constraints.NotBlank;

public record PresencaCreateDTO(
        @NotBlank(message = "Selecione a corrida para adicionar sua presença.")
        Long corridaID,

        @NotBlank(message = "Informe o Status.")
        String status){
}
