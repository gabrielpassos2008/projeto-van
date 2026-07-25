package com.gabriel.projeto_van.exception;

import com.gabriel.projeto_van.dto.Exception.MensagemErroDTO;
import com.gabriel.projeto_van.exception.exceptions.EmailJaExistenteException;
import com.gabriel.projeto_van.exception.exceptions.TokenInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailJaExistenteException.class)
    public ResponseEntity<MensagemErroDTO> emailJaExistente(EmailJaExistenteException exception){
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                HttpStatus.CONFLICT.name());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erro);
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<MensagemErroDTO> tokenInvalido (TokenInvalidoException exception){
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED.name());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(erro);
    }
}
