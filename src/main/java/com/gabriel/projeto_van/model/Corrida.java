package com.gabriel.projeto_van.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Corrida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    private String turno;

    @ManyToOne
    @JoinColumn(name = "fk_motorista_id")
    private Motorista motorista;


    @ManyToMany(mappedBy = "corridas")
    private List<Cliente> clientes = new ArrayList<>();
}
