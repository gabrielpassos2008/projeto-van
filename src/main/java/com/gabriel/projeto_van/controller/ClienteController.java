package com.gabriel.projeto_van.controller;

import com.gabriel.projeto_van.dto.corrida.CorridaResponseDTO;
import com.gabriel.projeto_van.dto.presenca.PresencaCreateDTO;
import com.gabriel.projeto_van.service.CorridaService;
import com.gabriel.projeto_van.service.PresencaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private PresencaService presencaService;

    @Autowired
    private CorridaService corridaService;

    @PostMapping("/registrar/presenca")
    public ResponseEntity<String> postResgistrarPresenca (@RequestBody @Valid PresencaCreateDTO dto){
        String mensagem = presencaService.criarPresensa(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mensagem);
    }

    @GetMapping("/listar/corrida")
    public ResponseEntity<List<CorridaResponseDTO>> getListarCorrida(){
        List<CorridaResponseDTO> lista = corridaService.ListarCorridaPorCliente();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }
}
