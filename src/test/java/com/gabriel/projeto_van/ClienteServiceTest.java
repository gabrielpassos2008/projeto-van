package com.gabriel.projeto_van;

import com.gabriel.projeto_van.exception.exceptions.UsuarioNaoEncontradoException;
import com.gabriel.projeto_van.model.Cliente;
import com.gabriel.projeto_van.model.Corrida;
import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.repository.ClienteRepository;
import com.gabriel.projeto_van.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    //@Mock para o repository
    @Mock
    private ClienteRepository clienteRepository;

    //@InjectMocks para o service
    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteTeste;

    // @BeforeEach para cria o objeto base
    @BeforeEach
    void setUp(){
        Motorista motorista = new Motorista();
        motorista.setId(1L);
        Corrida corrida = new Corrida();
        corrida.setId(1L);

        clienteTeste = new Cliente();

        clienteTeste.setId(1L);
        clienteTeste.setEmail("clienteTeste@gmail.com");
        clienteTeste.setSenha("123");
        clienteTeste.setNome("cliente teste");
        clienteTeste.setTelefone("51999999999");
        clienteTeste.setMotorista(motorista);
        clienteTeste.getCorridas().add(corrida);
    }

    @Test
    void validarRetornarClientePorEmail_quandoEmailExiste(){
        //Quando alguém chamar findByEmail com esse email, devolva esse cliente.
        when(clienteRepository.findByEmail("clienteTeste@gmail.com"))
                .thenReturn(Optional.of(clienteTeste));

        Cliente resultado = clienteService.retornarClientePorEmail("clienteTeste@gmail.com");
        //Compara o valor esperado com o valor do resultado
        assertEquals(clienteTeste, resultado);
        //Verifique se findByEmail foi realmente chamado com esse email.
        verify(clienteRepository).findByEmail("clienteTeste@gmail.com");
    }

    @Test
    void validarRetornarClientePorEmail_quandoEmailNaoExiste(){
        //Optional.empty() representa um Objeto vazio
        when(clienteRepository.findByEmail("clienteTeste@gmail.com")).thenReturn(Optional.empty());
                                                            // fazendo a pesquisa aqui
        assertThrows(UsuarioNaoEncontradoException.class, () -> clienteService.retornarClientePorEmail("clienteTeste@gmail.com"));

        //Verifique se findByEmail foi realmente chamado com esse email.
        verify(clienteRepository).findByEmail("clienteTeste@gmail.com");
    }
}
