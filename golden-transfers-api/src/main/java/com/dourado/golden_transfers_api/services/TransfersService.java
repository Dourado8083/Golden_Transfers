package com.dourado.golden_transfers_api.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dourado.golden_transfers_api.dto.TransfersRequestDto;
import com.dourado.golden_transfers_api.dto.TransfersResponseDTO;
import com.dourado.golden_transfers_api.model.Transfers;
import com.dourado.golden_transfers_api.repository.TransfersRepository;
import com.dourado.golden_transfers_api.strategy.TransfersStategyFactory;
import com.dourado.golden_transfers_api.strategy.TransfersStrategy;

@Service
public class TransfersService {
    @Autowired
    private TransfersRepository repository;

    @Autowired
    private TransfersStategyFactory TransfersStategyFactory;

    public TransfersResponseDTO agendar(TransfersRequestDto dto) {
        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(hoje, dto.getDataTransferencia());

       TransfersStrategy strategy = TransfersStategyFactory.getStrategy(dias);
        BigDecimal taxa = strategy.calcular(dias, dto.getValor());

        Transfers transferencia = new Transfers();
        transferencia.setContaOrigem(dto.getContaOrigem());
        transferencia.setContaDestino(dto.getContaDestino());
        transferencia.setValor(dto.getValor());
        transferencia.setTaxa(taxa);
        transferencia.setDataTransferencia(dto.getDataTransferencia());
        transferencia.setDataAgendamento(hoje);

        Transfers salvo = repository.save(transferencia);
        return TransfersResponseDTO.fromEntity(salvo);
    }

    public List<TransfersResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(TransfersResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
