package com.gabriel.projeto_van.repository;

import com.gabriel.projeto_van.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
