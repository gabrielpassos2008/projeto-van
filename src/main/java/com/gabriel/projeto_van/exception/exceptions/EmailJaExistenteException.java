package com.gabriel.projeto_van.exception.exceptions;

public class EmailJaExistenteException extends RuntimeException {
    public EmailJaExistenteException(String message) {
        super(message);
    }

    public EmailJaExistenteException(){
        super("Email ja existente no sistema, crie com outro email");
    }
}
