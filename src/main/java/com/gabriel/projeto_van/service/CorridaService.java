package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.corrida.CorridaCreateDTO;
import com.gabriel.projeto_van.dto.corrida.CorridaResponseDTO;
import com.gabriel.projeto_van.exception.exceptions.CorridaJaCadastradaException;
import com.gabriel.projeto_van.model.Corrida;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.repository.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorridaService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private CorridaRepository corridaRepository;

    public CorridaResponseDTO registrarCorrida(CorridaCreateDTO dto){
        this.validarNomeCorrida(dto);

        String email = tokenService.retornarUsuarioPeloEmailDaAuntenticacao();
        Motorista motorista = motoristaService.retornarMotoristaEmail(email);

        Corrida corrida = new Corrida();

        corrida.setNome(dto.nome());
        corrida.setTurno(dto.turno());
        corrida.setMotorista(motorista);
        this.corridaRepository.save(corrida);

        return new CorridaResponseDTO(corrida.getId(), corrida.getNome(), corrida.getTurno());

    }

    public void validarNomeCorrida(CorridaCreateDTO dto){
        String email = tokenService.retornarUsuarioPeloEmailDaAuntenticacao();
        Motorista motorista = motoristaService.retornarMotoristaEmail(email);
        if(corridaRepository.existsByNomeAndMotorista(dto.nome(),motorista)){
            throw new CorridaJaCadastradaException();
        };
    }
}
