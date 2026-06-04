package com.dourado.golden_transfers_api.dto;

import com.dourado.golden_transfers_api.model.Transfers;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransfersResponseDTO {

    private Long id;
    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }

    public String getContaDestino() { return contaDestino; }
    public void setContaDestino(String contaDestino) { this.contaDestino = contaDestino; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public BigDecimal getTaxa() { return taxa; }
    public void setTaxa(BigDecimal taxa) { this.taxa = taxa; }

    public LocalDate getDataTransferencia() { return dataTransferencia; }
    public void setDataTransferencia(LocalDate dataTransferencia) { this.dataTransferencia = dataTransferencia; }

    public LocalDate getDataAgendamento() { return dataAgendamento; }
    public void setDataAgendamento(LocalDate dataAgendamento) { this.dataAgendamento = dataAgendamento; }

    public static TransfersResponseDTO fromEntity(Transfers t) {
        TransfersResponseDTO dto = new TransfersResponseDTO();
        dto.setId(t.getId());
        dto.setContaOrigem(t.getContaOrigem());
        dto.setContaDestino(t.getContaDestino());
        dto.setValor(t.getValor());
        dto.setTaxa(t.getTaxa());
        dto.setDataTransferencia(t.getDataTransferencia());
        dto.setDataAgendamento(t.getDataAgendamento());
        return dto;
    }
}