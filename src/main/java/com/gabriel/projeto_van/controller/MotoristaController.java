package com.gabriel.projeto_van.controller;

import com.gabriel.projeto_van.dto.cliente.ClienteCreateDTO;
import com.gabriel.projeto_van.dto.cliente.ClienteReponseDTO;
import com.gabriel.projeto_van.dto.motorista.MotoristaCreateDTO;
import com.gabriel.projeto_van.dto.motorista.MotoristaResponseDTO;
import com.gabriel.projeto_van.service.ClienteService;
import com.gabriel.projeto_van.service.MotoristaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private ClienteService clienteService;



    @PostMapping("/registrarCLiente")
    public ResponseEntity<ClienteReponseDTO> postRegistrar(@RequestBody @Valid ClienteCreateDTO dto){
        ClienteReponseDTO clienteReponseDTO= clienteService.registrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteReponseDTO);
    }
}
