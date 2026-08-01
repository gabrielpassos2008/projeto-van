package com.gabriel.projeto_van.repository;

import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Corrida;
import com.gabriel.projeto_van.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    boolean existsByEmail(String email);

    List<Cliente> findByMotorista(Motorista motorista);
    // Containing → busca por parte do nome (não precisa ser igual)
    // IgnoreCase → ignora maiúsculas e minúsculas
    List<Cliente> findByMotoristaAndNomeContainingIgnoreCase(Motorista motorista, String nome);


}
