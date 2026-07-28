package com.gabriel.projeto_van.exception.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradoException(){
        super("Usuario não encontrado");
    }
}
