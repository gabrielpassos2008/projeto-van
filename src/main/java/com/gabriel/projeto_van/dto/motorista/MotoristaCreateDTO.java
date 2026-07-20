package com.gabriel.projeto_van.dto.motorista;

import com.gabriel.projeto_van.model.Role;

public record MotoristaCreateDTO(String nome, String email, String senha, String telefone, Role role) {
}
