package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.motorista.MotoristaCreateDTO;
import com.gabriel.projeto_van.dto.motorista.MotoristaResponseDTO;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    public MotoristaResponseDTO registrar (MotoristaCreateDTO dto){
        Motorista motorista = new Motorista();

        UsuarioLogin usuarioLogin = usuarioLoginService.registrar(dto.email(), dto.senha(), dto.role());
        motorista.setNome(dto.nome());
        motorista.setTelefone(dto.telefone());

        motorista.setEmail(usuarioLogin.getEmail());
        motorista.setSenha(usuarioLogin.getSenha());
        motorista.setUsuarioLogin(usuarioLogin);

        Motorista salvo = motoristaRepository.save(motorista);

        return new MotoristaResponseDTO(salvo.getId(), salvo.getEmail(), salvo.getNome());
    }
}
