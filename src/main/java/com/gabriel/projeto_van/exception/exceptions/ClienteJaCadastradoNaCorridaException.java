package com.gabriel.projeto_van.exception.exceptions;

public class ClienteJaCadastradoNaCorridaException extends RuntimeException {
    public ClienteJaCadastradoNaCorridaException(String message) {
        super(message);
    }
    public ClienteJaCadastradoNaCorridaException(){
        super("O cliente já está cadastrado nesta corrida.");
    }
}
