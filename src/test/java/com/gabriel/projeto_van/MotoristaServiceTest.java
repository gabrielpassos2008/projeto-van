package com.gabriel.projeto_van;

import com.gabriel.projeto_van.model.Motorista;
import com.gabriel.projeto_van.repository.MotoristaRepository;
import com.gabriel.projeto_van.service.MotoristaService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MotoristaServiceTest {
    @Mock
    private MotoristaRepository motoristaRepository;

    @InjectMocks
    private MotoristaService motoristaService;

    private Motorista motoristaTeste;

    @BeforeEach
    void setUp(){
        motoristaTeste = new Motorista();

        motoristaTeste.setId(1l);
    }
}
