package com.gabriel.projeto_van.repository;

import com.gabriel.projeto_van.model.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin,Long> {
    Optional<UsuarioLogin> findByEmail(String email);
}
