package com.gabriel.projeto_van.config;

import com.gabriel.projeto_van.dto.Exception.MensagemErroDTO;
import com.gabriel.projeto_van.exception.exceptions.TokenInvalidoException;
import com.gabriel.projeto_van.repository.UsuarioLoginRepository;
import com.gabriel.projeto_van.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioLoginRepository usuarioLoginRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request); // pega o token da requisiçao
        try {
            if (token != null){ // verifica se e null
                String email = tokenService.validarToken(token); // retorna o email que esta no token
                UserDetails usuario = usuarioLoginRepository.findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado")); //verificando o usuario no banco

                UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities()); // criando a autenticacao
                SecurityContextHolder.getContext().setAuthentication(autenticacao); // registrando o login do usuario
            }
            filterChain.doFilter(request,response);
        } catch (TokenInvalidoException e ) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // setando para aparecer o codigo Http
            response.setContentType("application/json"); // setando o tipo de conteudo q vai ser enviado

            // criando dto para enviar a mensagem de erro
            MensagemErroDTO erro = new MensagemErroDTO(
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED.name());
            // objectMapper serve para converter objeto java em json
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(),erro);

        }

    }


    //Metedo que extrai o token da requisicao
    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        return authHeader.substring(7);
    }


}
