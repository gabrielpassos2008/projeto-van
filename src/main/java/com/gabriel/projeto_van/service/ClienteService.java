package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.cliente.ClienteCreateDTO;
import com.gabriel.projeto_van.dto.cliente.ClienteReponseDTO;
import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.repository.ClienteRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteReponseDTO registrar (ClienteCreateDTO dto){
        Cliente cliente = new Cliente();

        cliente.setEmail(dto.email());
        cliente.setSenha(dto.senha());
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());

        this.clienteRepository.save(cliente);
        return new ClienteReponseDTO(cliente.getId(), cliente.getEmail(), cliente.getNome());
    }
}
