package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.motorista.MotoristaCreateDTO;
import com.gabriel.projeto_van.dto.motorista.MotoristaResponseDTO;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public MotoristaResponseDTO registrar (MotoristaCreateDTO dto){
        Motorista motorista = new Motorista();

        motorista.setNome(dto.nome());
        motorista.setEmail(dto.email());
        motorista.setSenha(dto.senha());
        motorista.setTelefone(dto.telefone());

        this.motoristaRepository.save(motorista);

        return new MotoristaResponseDTO(motorista.getId(), motorista.getEmail(), motorista.getNome());
    }
}
