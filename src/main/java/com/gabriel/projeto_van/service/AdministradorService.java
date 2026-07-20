package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.administrador.AdministradorCreateDTO;
import com.gabriel.projeto_van.dto.administrador.AdministradorResponseDTO;
import com.gabriel.projeto_van.model.Administrador;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.repository.AdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @Transactional
    public AdministradorResponseDTO registrar (AdministradorCreateDTO dto){

        UsuarioLogin usuarioLogin = usuarioLoginService.registrar(dto);

        Administrador administrador = new Administrador();
        administrador.setEmail(usuarioLogin.getEmail());
        administrador.setSenha(usuarioLogin.getSenha());
        administrador.setUsuarioLogin(usuarioLogin);

        Administrador salvo = repository.save(administrador);

        return new AdministradorResponseDTO(salvo.getId(), salvo.getEmail());
    }
}
