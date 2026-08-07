package com.gabriel.projeto_van.repository;

import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Corrida;
import com.gabriel.projeto_van.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CorridaRepository extends JpaRepository<Corrida,Long> {

    Optional<Corrida> findByNomeAndMotorista(String nome, Motorista motorista);

    boolean existsByNomeAndMotorista(String nome, Motorista motorista);


    List<Corrida> findByMotorista(Motorista motorista);

    // Containing → busca por parte do nome (não precisa ser igual)
    // IgnoreCase → ignora maiúsculas e minúsculas
    List<Corrida> findByMotoristaAndNomeContainingIgnoreCase(Motorista motorista,String nome);

    List<Corrida> findByClientes(Cliente cLiente);
}
