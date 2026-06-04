package com.dourado.golden_transfers_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.*;

public class TransfersRequestDto {
    @NotBlank(message = "Conta de origem precisa conter valor")
    @Pattern(regexp = "\\d{10}", message = "Conta de origem deve ter exatamente 10 dígitos")
    private String contaOrigem;
 
    @NotBlank(message = "Conta de destino precisaconter valor")
    @Pattern(regexp = "\\d{10}", message = "Conta de destino deve ter exatamente 10 dígitos")
    private String contaDestino;
 
    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;
 
}
