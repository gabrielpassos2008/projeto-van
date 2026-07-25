package com.gabriel.projeto_van.exception.exceptions;

public class TokenInvalidoException extends RuntimeException {
    public TokenInvalidoException(String message) {
        super(message);
    }

    public TokenInvalidoException(){
        super("Token inválido ou expirado.");
    }
}
