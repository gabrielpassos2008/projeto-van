package com.gabriel.projeto_van.exception.exceptions;

public class CorridaNaoEncontradaException extends RuntimeException {
    public CorridaNaoEncontradaException(String message) {
        super(message);
    }

    public CorridaNaoEncontradaException(){
        super("Corrida não encontrada para o motorista informado.");
    }
}
