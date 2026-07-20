package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.administrador.AdministradorCreateDTO;
import com.gabriel.projeto_van.model.Role;
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

    public UsuarioLogin registrar(String email, String senha, Role role){
        UsuarioLogin usuarioNovo = new UsuarioLogin();

        usuarioNovo.setEmail(email);
        usuarioNovo.setSenha(passwordEncoder.encode(senha));
        usuarioNovo.setRole(role);

        return repository.save(usuarioNovo);
    }
}
