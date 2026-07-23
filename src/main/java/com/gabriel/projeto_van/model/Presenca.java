package com.gabriel.projeto_van.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;
    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_motorista_id")
    private Motorista motorista;

    @ManyToOne
    @JoinColumn(name = "fk_corrida_id")
    private Corrida corrida;
}
