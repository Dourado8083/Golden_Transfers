package com.dourado.golden_transfers_api.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TransfersEleventoVinte {
   private final TransfersStrategy taxa = new TransfersElevenAVinte();

    @Test
    void deveCalcular82PorCento() {
        BigDecimal resultado = taxa.calcular(15, new BigDecimal("100.00"));
        assertEquals(new BigDecimal("8.20"), resultado);
    }

    @Test
    void deveCalcular82PorCentoNo11Dia() {
        BigDecimal resultado = taxa.calcular(11, new BigDecimal("100.00"));
        assertEquals(new BigDecimal("8.20"), resultado);
    } 
}
