package com.gabriel.projeto_van.exception;

public record MensagemErroDTO(
        int status,
        String mensagem,
        String erro) {
}
