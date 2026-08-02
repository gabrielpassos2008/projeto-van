package com.gabriel.projeto_van.exception;

import com.gabriel.projeto_van.dto.Exception.MensagemErroDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Executado quando o usuário tenta acessar uma rota protegida
    // sem estar autenticado, como quando não informa um token válido.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.UNAUTHORIZED.value(),
                "Token não informado ou inválido",
                HttpStatus.UNAUTHORIZED.name());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(),erro);

    }
}
