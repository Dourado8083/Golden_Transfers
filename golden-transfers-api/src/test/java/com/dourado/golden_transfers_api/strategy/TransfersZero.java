package com.dourado.golden_transfers_api.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TransfersZero {
  private final TransfersStrategy taxa = new TransfersZeroDayRate();

    @Test
    void deveCalcularTaxaParaDiaZero() {
        BigDecimal resultado = taxa.calcular(0, new BigDecimal("100.00"));
        assertEquals(new BigDecimal("5.50"), resultado); 
    }

    @Test
    void deveCalcularTaxaComValorAlto() {
        BigDecimal resultado = taxa.calcular(0, new BigDecimal("1000.00"));
        assertEquals(new BigDecimal("28.00"), resultado); 
    }  
}
