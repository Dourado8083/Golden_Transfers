package com.dourado.golden_transfers_api.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TransfersOneToTen {
       private final TransfersStrategy taxa = new TransfersRateOneToTen();

    @Test
    void deveRetornarDozePara1Dia() {
        assertEquals(new BigDecimal("12.00"), taxa.calcular(1, new BigDecimal("500.00")));
    }

    @Test
    void deveRetornarDozePara5Dias() {
        assertEquals(new BigDecimal("12.00"), taxa.calcular(5, new BigDecimal("1000.00")));
    }

    @Test
    void deveRetornarDozePara10Dias() {
        assertEquals(new BigDecimal("12.00"), taxa.calcular(10, new BigDecimal("9999.00")));
    } 
}
