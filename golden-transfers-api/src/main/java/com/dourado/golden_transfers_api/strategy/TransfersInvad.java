package com.dourado.golden_transfers_api.strategy;
import java.math.BigDecimal;

public class TransfersInvad implements  TransfersStrategy{
     @Override
    public BigDecimal calcular(long dias, BigDecimal valor) {
        throw new IllegalArgumentException(
            "Nenhuma taxa aplicável para transferências com prazo de " + dias + " dias."
        );
    }   
}