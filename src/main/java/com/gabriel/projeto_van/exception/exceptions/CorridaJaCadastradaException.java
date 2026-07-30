package com.gabriel.projeto_van.exception.exceptions;

public class CorridaJaCadastradaException extends RuntimeException {
    public CorridaJaCadastradaException(String message) {
        super(message);
    }

    public CorridaJaCadastradaException(){
        super("Você já possui uma corrida com esse nome");
    }


}
