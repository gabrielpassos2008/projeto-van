package com.gabriel.projeto_van.dto.motorista;

import com.gabriel.projeto_van.model.Role;
import jakarta.validation.constraints.NotBlank;

public record MotoristaCreateDTO(

        // as validacoes tem q ficar nos dtos


        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Email é obrigatório")
        String email,
        @NotBlank(message = "senha é obrigatório")
        String senha,
        @NotBlank(message = "Nome é obrigatório")
        String telefone,

        Role role) {
}
