package com.gabriel.projeto_van.exception.exceptions;

public class ClienteNaoPertenceAoMotoristaException extends RuntimeException {
    public ClienteNaoPertenceAoMotoristaException(String message) {
        super(message);
    }
    public ClienteNaoPertenceAoMotoristaException(){
        super("O cliente não pertence ao motorista.");
    }
}
