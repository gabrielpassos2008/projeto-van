package com.gabriel.projeto_van.exception.exceptions;

public class EmailJaExistenteException extends RuntimeException {
    public EmailJaExistenteException(String message) {
        super(message);
    }

    public EmailJaExistenteException(){
        super("Este e-mail já está cadastrado. Informe outro endereço de e-mail.");
    }
}
