package com.gabriel.projeto_van.controller;

import com.gabriel.projeto_van.dto.presenca.PresencaCreateDTO;
import com.gabriel.projeto_van.service.PresencaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private PresencaService presencaService;

    @PostMapping("/registrar/presenca")
    public ResponseEntity<String> postResgistrarPresenca (@RequestBody @Valid PresencaCreateDTO dto){
        String mensagem = presencaService.criarPresensa(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mensagem);
    }
}
