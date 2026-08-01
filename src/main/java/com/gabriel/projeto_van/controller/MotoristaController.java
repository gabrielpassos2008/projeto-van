package com.gabriel.projeto_van.controller;

import com.gabriel.projeto_van.dto.cliente.ClienteCreateDTO;
import com.gabriel.projeto_van.dto.cliente.ClienteReponseDTO;
import com.gabriel.projeto_van.dto.corrida.CorridaCreateDTO;
import com.gabriel.projeto_van.dto.corrida.CorridaPesquisaDTO;
import com.gabriel.projeto_van.dto.corrida.CorridaResponseDTO;
import com.gabriel.projeto_van.service.ClienteService;
import com.gabriel.projeto_van.service.CorridaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CorridaService corridaService;

    @PostMapping("/registrarCLiente")
    public ResponseEntity<ClienteReponseDTO> postRegistrar(@RequestBody @Valid ClienteCreateDTO dto){
        ClienteReponseDTO clienteReponseDTO= clienteService.registrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteReponseDTO);
    }
    @PostMapping("/registrarCorrida")
    public ResponseEntity<CorridaResponseDTO> postRegistrarCorrida(@RequestBody @Valid CorridaCreateDTO dto){
        CorridaResponseDTO corridaResponseDTO = corridaService.registrarCorrida(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(corridaResponseDTO);
    }

    @GetMapping("/listarCorrida")
    public ResponseEntity<List<CorridaResponseDTO>> getListarCorrida(){
        List<CorridaResponseDTO> lista = corridaService.listarCorrida();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }
    @GetMapping("listarCliente")
    public ResponseEntity<List<ClienteReponseDTO>> getListarCliente(){
        List<ClienteReponseDTO> lista = clienteService.listarCliente();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }

    @GetMapping("/pesquisarCorrida")
    public ResponseEntity<List<CorridaResponseDTO>> getListarCorridaPorNome(@RequestBody @Valid CorridaPesquisaDTO dto){
        List<CorridaResponseDTO> lista = corridaService.listarCorridaPorNome(dto.pesquisa());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }
}
