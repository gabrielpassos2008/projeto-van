package com.gabriel.projeto_van.dto.Exception;

public record MensagemErroDTO(
        int status,
        String mensagem,
        String erro) {
}
