package com.dourado.golden_transfers_api.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class  TransfersZeroDayRate implements  TransfersStrategy{
    @Override
    public BigDecimal calcular(long dias, BigDecimal valor) {
        return new BigDecimal("3.00").add(valor.multiply(new BigDecimal("0.025")));
    }
}