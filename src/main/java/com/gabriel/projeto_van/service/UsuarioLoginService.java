package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.administrador.AdministradorCreateDTO;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.repository.UsuarioLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLoginService {

    @Autowired
    private UsuarioLoginRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioLogin registrar(AdministradorCreateDTO dto){
        UsuarioLogin usuarioNovo = new UsuarioLogin();

        usuarioNovo.setEmail(dto.email());
        usuarioNovo.setSenha(passwordEncoder.encode(dto.senha()));
        usuarioNovo.setRole(dto.role());

        return repository.save(usuarioNovo);
    }
}
