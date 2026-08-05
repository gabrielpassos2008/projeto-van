package com.gabriel.projeto_van.service;

import com.gabriel.projeto_van.dto.cliente.ClienteCreateDTO;
import com.gabriel.projeto_van.dto.cliente.ClienteReponseDTO;
import com.gabriel.projeto_van.dto.corrida.CorridaResponseDTO;
import com.gabriel.projeto_van.exception.exceptions.EmailJaExistenteException;
import com.gabriel.projeto_van.exception.exceptions.UsuarioNaoEncontradoException;
import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.model.UsuarioLogin;
import com.gabriel.projeto_van.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private TokenService tokenService;


    public ClienteReponseDTO registrar (ClienteCreateDTO dto){
        if (clienteRepository.existsByEmail(dto.email())){
            throw new EmailJaExistenteException("Este e-mail já está cadastrado. Informe outro endereço de e-mail.");
        }

        Cliente cliente = new Cliente();

        String email = tokenService.retornarUsuarioPeloEmailDaAuntenticacao();
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
        return new ClienteReponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }

    public List<ClienteReponseDTO> listarCliente(){
        Motorista motorista = motoristaService.retornarMotoristaAutenticado();

        return clienteRepository.findByMotorista(motorista)
                .stream()
                .map(cliente -> new ClienteReponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail()))
                .toList();
    }

    public List<ClienteReponseDTO> listarCLientePorNome(String pesquisa){
        Motorista motorista = motoristaService.retornarMotoristaAutenticado();
        return clienteRepository.findByMotoristaAndNomeContainingIgnoreCase(motorista,pesquisa)
                .stream()
                .map(cliente -> new ClienteReponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail()))
                .toList();
    }

    public Cliente retornarClientePorId(Long id){
        return clienteRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException("Cliente não encontrada"));
    }
    public Cliente retornarClientePorEmail(String email){
        return clienteRepository.findByEmail(email).orElseThrow(()-> new UsuarioNaoEncontradoException("Cliente não encontrada"));
    }

    public Cliente retornarClienteAutenticado(){
        String email = tokenService.retornarUsuarioPeloEmailDaAuntenticacao();
        return retornarClientePorEmail(email);
    }
}
