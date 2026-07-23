package com.gabriel.projeto_van.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "fk_Usuario_login_id")
    private UsuarioLogin usuarioLogin;

    @ManyToOne
    @JoinColumn(name = "fk_motorista_id")
    private Motorista motorista;
}
