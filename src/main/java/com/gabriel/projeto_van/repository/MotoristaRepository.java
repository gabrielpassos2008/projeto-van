package com.gabriel.projeto_van.repository;

import com.gabriel.projeto_van.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoristaRepository extends JpaRepository<Motorista ,Long> {

    Optional<Motorista> findByEmail(String email);

}
