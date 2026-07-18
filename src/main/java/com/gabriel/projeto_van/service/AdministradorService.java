package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.administrador.AdministradorCreateDTO;
import com.gabriel.projeto_van.dto.administrador.AdministradorResponseDTO;
import com.gabriel.projeto_van.model.Administrador;
import com.gabriel.projeto_van.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public AdministradorResponseDTO registrar (AdministradorCreateDTO dto){
        Administrador administrador = new Administrador();

        administrador.setEmail(dto.email());
        administrador.setSenha(dto.senha());

        Administrador salvo = repository.save(administrador);

        return new AdministradorResponseDTO(salvo.getId(), salvo.getEmail());
    }
}
