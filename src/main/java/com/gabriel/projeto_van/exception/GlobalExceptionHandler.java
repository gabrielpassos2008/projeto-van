package com.gabriel.projeto_van.exception;

import com.gabriel.projeto_van.dto.Exception.MensagemErroDTO;
import com.gabriel.projeto_van.exception.exceptions.EmailJaExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailJaExistenteException.class)
    public ResponseEntity<MensagemErroDTO> emailJaExistente(EmailJaExistenteException excecao){
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.CONFLICT.value(),
                excecao.getMessage(),
                HttpStatus.CONFLICT.name());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }
}
