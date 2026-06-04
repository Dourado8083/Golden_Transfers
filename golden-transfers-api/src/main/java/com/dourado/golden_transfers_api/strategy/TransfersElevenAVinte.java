package com.dourado.golden_transfers_api.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class TransfersElevenAVinte implements  TransfersStrategy {
     @Override
    public BigDecimal calcular(long dias, BigDecimal valor) {
        return valor.multiply(new BigDecimal("0.082"));
    } 
}
