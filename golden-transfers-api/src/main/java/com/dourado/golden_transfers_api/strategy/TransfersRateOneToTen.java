package com.dourado.golden_transfers_api.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
@Component
public class TransfersRateOneToTen implements  TransfersStrategy {
  @Override
    public BigDecimal calcular(long dias, BigDecimal valor) {
        return new BigDecimal("12.00");
    }   
}
