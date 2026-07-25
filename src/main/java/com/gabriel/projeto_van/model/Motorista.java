package com.gabriel.projeto_van.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "senha é obrigatório")
    private String senha;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "fk_administrador_id")
    private Administrador administrador;

    @ManyToOne
    @JoinColumn(name = "fk_Usuario_login_id")
    private UsuarioLogin usuarioLogin;

}
