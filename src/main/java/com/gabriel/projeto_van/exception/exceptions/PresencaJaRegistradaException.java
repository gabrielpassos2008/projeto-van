package com.gabriel.projeto_van.exception.exceptions;

public class PresencaJaRegistradaException extends RuntimeException {
    public PresencaJaRegistradaException(String message) {
        super(message);
    }
    public PresencaJaRegistradaException(){
        super("Já existe uma presença registrada para este cliente nesta corrida.");
    }
}
