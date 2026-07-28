package com.gabriel.projeto_van.exception;

import com.gabriel.projeto_van.dto.Exception.MensagemErroDTO;
import com.gabriel.projeto_van.exception.exceptions.EmailJaExistenteException;
import com.gabriel.projeto_van.exception.exceptions.TokenInvalidoException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends  ResponseEntityExceptionHandler{

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                            HttpHeaders headers,
                                                                            HttpStatusCode statusCode,
                                                                            WebRequest request) {
        String mensagem = exception
                .getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        MensagemErroDTO erro = new MensagemErroDTO(
                statusCode.value(),
                mensagem,
                statusCode.toString());

        return ResponseEntity.status(statusCode).body(erro);
    }
}
