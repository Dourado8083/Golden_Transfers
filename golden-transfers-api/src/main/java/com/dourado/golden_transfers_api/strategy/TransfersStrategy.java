package com.dourado.golden_transfers_api.strategy;

import java.math.BigDecimal;

public interface TransfersStrategy {
    BigDecimal calcular(long dias, BigDecimal valor);
}
