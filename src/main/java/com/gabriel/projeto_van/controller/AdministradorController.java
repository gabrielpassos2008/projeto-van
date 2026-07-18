package com.gabriel.projeto_van.controller;

import com.gabriel.projeto_van.dto.administrador.AdministradorCreateDTO;
import com.gabriel.projeto_van.dto.administrador.AdministradorResponseDTO;
import com.gabriel.projeto_van.dto.motorista.MotoristaCreateDTO;
import com.gabriel.projeto_van.dto.motorista.MotoristaResponseDTO;
import com.gabriel.projeto_van.repository.AdministradorRepository;
import com.gabriel.projeto_van.service.AdministradorService;
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
@RequestMapping("/adm")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping("/teste")
    public ResponseEntity<AdministradorResponseDTO> postRegistrar(@RequestBody @Valid AdministradorCreateDTO dto){
        AdministradorResponseDTO administradorResponseDTO = administradorService.registrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(administradorResponseDTO);
    }

    @PostMapping("/registrarMotorista")
    public ResponseEntity<MotoristaResponseDTO> postRegistrar(@RequestBody @Valid MotoristaCreateDTO dto){
        MotoristaResponseDTO motoristaResponseDTO = motoristaService.registrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(motoristaResponseDTO);
    }

}
