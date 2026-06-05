package com.dourado.golden_transfers_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.*;

public class TransfersRequestDto {
    @NotBlank(message = "Conta de origem é obrigatória")
    @Pattern(regexp = "\\d{5}", message = "Conta de origem deve ter exatamente 5 dígitos")
    private String contaOrigem;

    @NotBlank(message = "Conta de destino é obrigatória")
    @Pattern(regexp = "\\d{5}", message = "Conta de destino deve ter exatamente 5 dígitos")
    private String contaDestino;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "Data de transferência é obrigatória")
    @FutureOrPresent(message = "Data de transferência não pode ser no passado")
    private LocalDate dataTransferencia;

    public String getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }

    public String getContaDestino() { return contaDestino; }
    public void setContaDestino(String contaDestino) { this.contaDestino = contaDestino; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getDataTransferencia() { return dataTransferencia; }
    public void setDataTransferencia(LocalDate dataTransferencia) { this.dataTransferencia = dataTransferencia;} 
 
}
