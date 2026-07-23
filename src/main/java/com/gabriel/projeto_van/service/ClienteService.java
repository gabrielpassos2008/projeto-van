package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.cliente.ClienteCreateDTO;
import com.gabriel.projeto_van.dto.cliente.ClienteReponseDTO;
import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @Autowired
    private MotoristaService motoristaService;


    public ClienteReponseDTO registrar (ClienteCreateDTO dto){
        Cliente cliente = new Cliente();

        String email = usuarioLoginService.retornarUsuarioPeloEmailDaAuntenticacao();
        Motorista motorista = motoristaService.retornarMotoristaEmail(email);


        UsuarioLogin usuarioLogin = usuarioLoginService.registrar(dto.email(), dto.senha(),dto.role());

        // adicionando os email e senha do UsuarioLogin
        cliente.setEmail(usuarioLogin.getEmail());
        cliente.setSenha(usuarioLogin.getSenha());
        // adicionar os conteudo pelo dtos
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());

        // adicionando as FK
        cliente.setMotorista(motorista);
        cliente.setUsuarioLogin(usuarioLogin);

        this.clienteRepository.save(cliente);
        return new ClienteReponseDTO(cliente.getId(), cliente.getEmail(), cliente.getNome());
    }
}
