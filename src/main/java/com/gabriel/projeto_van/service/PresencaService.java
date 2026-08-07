package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.presenca.PresencaCreateDTO;
import com.gabriel.projeto_van.exception.exceptions.PresencaJaRegistradaException;
import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Corrida;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.model.Presenca;
import com.gabriel.projeto_van.repository.CorridaRepository;
import com.gabriel.projeto_van.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void validarDiaPresenca(Corrida corrida, Cliente cliente){

        LocalDate hoje = LocalDate.now();
        // Define o início do dia atual (00:00:00)
        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        // Define o início do próximo dia para delimitar o fim da busca
        LocalDateTime inicioDoProximoDia = hoje.plusDays(1).atStartOfDay();
        boolean existe = presencaRepository.existePresencaNoDia(cliente,corrida,inicioDoDia,inicioDoProximoDia);
        if (existe){
            throw new PresencaJaRegistradaException();
        }
    }


    public String criarPresensa(PresencaCreateDTO dto){
        Corrida corrida = corridaService.retornarCorridaPorId(dto.corridaID());
        Cliente cliente = clienteService.retornarClienteAutenticado();

        validarDiaPresenca(corrida,cliente);

        Presenca presenca = new Presenca();

        presenca.setStatus(dto.status());
        presenca.setDataHora(LocalDateTime.now());
        presenca.setCliente(cliente);
        presenca.setMotorista(cliente.getMotorista());
        presenca.setCorrida(corrida);

        this.presencaRepository.save(presenca);
        return "Presença registrada com sucesso!";
    }
}
