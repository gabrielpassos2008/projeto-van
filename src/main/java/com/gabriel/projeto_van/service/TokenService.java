package com.gabriel.projeto_van.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabriel.projeto_van.model.UsuarioLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String chaveSecreta;

    public String gerarToken(UsuarioLogin usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            String token = JWT.create()
                    .withIssuer("Projeto van")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar o teken",exception);
        }
    }
    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            return JWT.require(algorithm)
                    .withIssuer("Projeto van")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            return "";
        }
    }

}
