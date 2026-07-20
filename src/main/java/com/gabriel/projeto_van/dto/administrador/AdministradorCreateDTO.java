package com.gabriel.projeto_van.dto.administrador;

import com.gabriel.projeto_van.model.Role;

public record AdministradorCreateDTO(String email, String senha, Role role) {
}
