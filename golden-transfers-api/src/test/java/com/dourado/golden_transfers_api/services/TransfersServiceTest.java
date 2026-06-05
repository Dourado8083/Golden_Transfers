package com.dourado.golden_transfers_api.services;


import com.dourado.golden_transfers_api.dto.TransfersResponseDTO;
import com.dourado.golden_transfers_api.model.Transfers;
import com.dourado.golden_transfers_api.repository.TransfersRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.dourado.golden_transfers_api.dto.TransfersRequestDto;
import com.dourado.golden_transfers_api.strategy.TransfersStategyFactory;

@ExtendWith(MockitoExtension.class)
class TransfersServiceTest {
//Em andamento

 @InjectMocks
    private TransfersService service;

    @Mock
    private TransfersRepository repository;

    @Mock
    private TransfersStategyFactory TransfersStategyFactory;

    private static final String CONTA_ORIGEM  = "1234567890";
    private static final String CONTA_DESTINO = "0987654321";
    private static final BigDecimal VALOR      = new BigDecimal("500.00");
    private static final BigDecimal TAXA        = new BigDecimal("5.50");
    private static final LocalDate  DATA_TRANSFERENCIA = LocalDate.now();
    private static final LocalDate  DATA_AGENDAMENTO   = LocalDate.now();

    private TransfersRequestDto dto;
    private Transfers transferenciaSalva;

    @BeforeEach
    void setup() {
        dto = new TransfersRequestDto();
        dto.setContaOrigem(CONTA_ORIGEM);
        dto.setContaDestino(CONTA_DESTINO);
        dto.setValor(VALOR);
        dto.setDataTransferencia(DATA_TRANSFERENCIA);

        transferenciaSalva = new Transfers();
        transferenciaSalva.setId(1L);
        transferenciaSalva.setContaOrigem(CONTA_ORIGEM);
        transferenciaSalva.setContaDestino(CONTA_DESTINO);
        transferenciaSalva.setValor(VALOR);
        transferenciaSalva.setTaxa(TAXA);
        transferenciaSalva.setDataTransferencia(DATA_TRANSFERENCIA);
        transferenciaSalva.setDataAgendamento(DATA_AGENDAMENTO);
    }

    @Test
    void deveAgendarTransferenciaComSucesso() {
        when(TransfersStategyFactory.getStrategy(anyLong()))
            .thenReturn((dias, valor) -> TAXA);
        when(repository.save(any(Transfers.class)))
            .thenReturn(transferenciaSalva);

        TransfersResponseDTO response = service.agendar(dto);

        assertNotNull(response);
        assertEquals(CONTA_ORIGEM, response.getContaOrigem());
        assertEquals(CONTA_DESTINO, response.getContaDestino());
        assertEquals(VALOR, response.getValor());
        assertEquals(TAXA, response.getTaxa());
        assertEquals(DATA_AGENDAMENTO, response.getDataAgendamento());
        verify(repository, times(1)).save(any(Transfers.class));
    }

    @Test
    void deveLancarExcecaoParaTaxaInvalida() {
        when(TransfersStategyFactory.getStrategy(anyLong()))
            .thenThrow(new IllegalArgumentException("Nenhuma taxa aplicável para o prazo informado."));

        assertThrows(IllegalArgumentException.class, () -> service.agendar(dto));
        verify(repository, never()).save(any());
    }

    @Test
    void deveListarTodasTransferencias() {
        when(repository.findAll()).thenReturn(List.of(transferenciaSalva));

        var lista = service.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(CONTA_ORIGEM, lista.get(0).getContaOrigem());
        assertEquals(TAXA, lista.get(0).getTaxa());
        verify(repository, times(1)).findAll();
    }

}