package com.gabriel.projeto_van.exception;

import com.gabriel.projeto_van.dto.Exception.MensagemErroDTO;
import com.gabriel.projeto_van.exception.exceptions.CorridaJaCadastradaException;
import com.gabriel.projeto_van.exception.exceptions.EmailJaExistenteException;
import com.gabriel.projeto_van.exception.exceptions.TokenInvalidoException;
import com.gabriel.projeto_van.exception.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<MensagemErroDTO> usuarioNaoEncontrado(UsuarioNaoEncontradoException exception){
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                HttpStatus.NOT_FOUND.name());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }


    @ExceptionHandler(CorridaJaCadastradaException.class)
    public  ResponseEntity<MensagemErroDTO> corridaNaoEncontrada(CorridaJaCadastradaException exception){
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                HttpStatus.CONFLICT.name());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erro);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, // Exceção lançada quando o @Valid encontra erros no DTO
            HttpHeaders headers, // Cabeçalhos HTTP da requisição
            HttpStatusCode statusCode, // Código HTTP que o Spring definiu (normalmente 400 BAD_REQUEST)
            WebRequest request // Informações da requisição atual
    ) {

        // Pega o erro de validação que aconteceu no campo do DTO
        // getBindingResult() contém todos os erros encontrados pelo @Valid
        // getFieldError() pega somente o primeiro erro encontrado
        // getDefaultMessage() pega a mensagem que você colocou no @NotBlank, @Size, etc.
        String mensagem = exception
                .getBindingResult()
                .getFieldError()
                .getDefaultMessage();


        MensagemErroDTO erro = new MensagemErroDTO(
                statusCode.value(), // Pega o número do status HTTP (400)
                mensagem, // Mensagem do @NotBlank, @Size...
                statusCode.toString() // Nome do status (BAD_REQUEST)
        );

        return ResponseEntity
                .status(statusCode)
                .body(erro);
    }

    //                Exceção de credenciais inválidas
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MensagemErroDTO> loginInvalido(BadCredentialsException exception){
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.UNAUTHORIZED.value(),
                "Email ou senha inválidos",
                HttpStatus.UNAUTHORIZED.name());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(erro);
    }
}
