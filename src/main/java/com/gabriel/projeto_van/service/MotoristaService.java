package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.motorista.MotoristaCreateDTO;
import com.gabriel.projeto_van.dto.motorista.MotoristaResponseDTO;
import com.gabriel.projeto_van.model.Administrador;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @Autowired
    private AdministradorService administradorService;


    public MotoristaResponseDTO registrar (MotoristaCreateDTO dto){
        Motorista motorista = new Motorista();

        String email = usuarioLoginService.retornarUsuarioPeloEmailDaAuntenticacao();
        Administrador administrador = administradorService.retornarPeloEmail(email);

        UsuarioLogin usuarioLogin = usuarioLoginService.registrar(dto.email(), dto.senha(), dto.role());
        motorista.setNome(dto.nome());
        motorista.setTelefone(dto.telefone());

        motorista.setEmail(usuarioLogin.getEmail());
        motorista.setSenha(usuarioLogin.getSenha());
        motorista.setUsuarioLogin(usuarioLogin);
        motorista.setAdministrador(administrador);

        Motorista salvo = motoristaRepository.save(motorista);

        return new MotoristaResponseDTO(salvo.getId(), salvo.getEmail(), salvo.getNome());
    }
}
