package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.administrador.AdministradorCreateDTO;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.repository.UsuarioLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteLoginService {

    @Autowired
    private UsuarioLoginRepository repository;

    public UsuarioLogin registrar(AdministradorCreateDTO usuario){
        UsuarioLogin usuarioNovo = new UsuarioLogin();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.senha());

        usuarioNovo.setEmail(usuario.email());
        usuarioNovo.setSenha(senhaCriptografada);
        usuarioNovo.setRole(usuario.role());


        return repository.save(usuarioNovo);
    }
}
