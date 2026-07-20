package com.gabriel.projeto_van.controller;


import com.gabriel.projeto_van.dto.auth.AutenticacaoDTO;
import com.gabriel.projeto_van.dto.auth.LoginResponseDTO;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.service.AuthorizationService;
import com.gabriel.projeto_van.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> postLogin (@RequestBody AutenticacaoDTO dto){
        UsernamePasswordAuthenticationToken usuarioEmailSenha = new UsernamePasswordAuthenticationToken(dto.email(),dto.senha());
        var auth = this.authenticationManager.authenticate(usuarioEmailSenha);
        String token = tokenService.gerarToken((UsuarioLogin) auth.getPrincipal());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new LoginResponseDTO(token));
    }
}
