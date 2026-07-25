package com.gabriel.projeto_van.repository;

import com.gabriel.projeto_van.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
}
