package com.dourado.golden_transfers_api.strategy;
import java.math.BigDecimal;

import com.dourado.golden_transfers_api.exceptions.TransferInvadiException;


public class TransfersInvad implements  TransfersStrategy{
     @Override
    public BigDecimal calcular(long dias, BigDecimal valor) {
     throw new TransferInvadiException(dias);
    }   
}