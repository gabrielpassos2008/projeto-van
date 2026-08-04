package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Corrida;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.model.Presenca;
import com.gabriel.projeto_van.repository.CorridaRepository;
import com.gabriel.projeto_van.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private CorridaService corridaService;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CorridaRepository corridaRepository;




    public String criarPresensa(Long corridaId, Long clienteId){
        // validar se a ja existe aquela corrida para o cliente na mesma data e no mesmo dia.

        Presenca presenca = new Presenca();

        Corrida corrida = corridaService.retornarCorridaPorId(corridaId);
        Cliente cliente = clienteService.retornarClientePorId(clienteId);

        this.motoristaService.validarSeClientePertenceAoMotorisra(corrida,cliente);

        Motorista motorista = motoristaService.retornarMotoristaAutenticado();

        presenca.setStatus("Vai amanha...");
        presenca.setDataHora(LocalDateTime.now());
        presenca.setCliente(cliente);
        presenca.setMotorista(motorista);
        presenca.setCorrida(corrida);

        this.presencaRepository.save(presenca);
        return "Presença registrada com sucesso!";
    }
}
