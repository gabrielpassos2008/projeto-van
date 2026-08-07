package com.gabriel.projeto_van.repository;

import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Corrida;
import com.gabriel.projeto_van.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PresencaRepository extends JpaRepository<Presenca,Long> {
    // Verifica se já existe uma presença para o cliente e a corrida
    // dentro do intervalo do dia informado.
    @Query(" SELECT COUNT(p) > 0 FROM Presenca p WHERE p.cliente = :cliente AND p.corrida = :corrida AND p.dataHora >= :inicioDoDia AND p.dataHora < :inicioDoProximoDia ")
    boolean existePresencaNoDia(@Param("cliente") Cliente cliente,
                                @Param("corrida") Corrida corrida,
                                @Param("inicioDoDia") LocalDateTime inicioDoDia,
                                @Param("inicioDoProximoDia") LocalDateTime inicioDoProximoDia );
}
