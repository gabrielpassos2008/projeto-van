package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.presenca.PresencaCreateDTO;
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

    public String criarPresensa(PresencaCreateDTO dto){
        // validar se a já existe aquela corrida para o cliente na mesma data e no mesmo dia.

        Presenca presenca = new Presenca();

        Corrida corrida = corridaService.retornarCorridaPorId(dto.corridaID());

        Cliente cliente = clienteService.retornarClienteAutenticado();
        Motorista motorista = motoristaService.retornarMotoristaAutenticado();

        this.motoristaService.validarSeClientePertenceAoMotorisra(corrida,cliente);


        presenca.setStatus(dto.status());
        presenca.setDataHora(LocalDateTime.now());
        presenca.setCliente(cliente);
        presenca.setMotorista(motorista);
        presenca.setCorrida(corrida);

        this.presencaRepository.save(presenca);
        return "Presença registrada com sucesso!";
    }
}
