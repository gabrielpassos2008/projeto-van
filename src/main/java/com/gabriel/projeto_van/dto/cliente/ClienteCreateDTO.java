package com.gabriel.projeto_van.dto.cliente;

import com.gabriel.projeto_van.model.Role;

public record ClienteCreateDTO(String nome, String email, String senha, String telefone, Role role) {
}
