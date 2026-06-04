package com.dourado.golden_transfers_api.strategy;

import org.springframework.stereotype.Component;

@Component
public class TransfersStategyFactory {
    public TransfersStrategy getStrategy(long dias) {
        if (dias == 0)        return new TransfersZeroDayRate();
        if (dias <= 10)       return new TransfersRateOneToTen();
        if (dias <= 20)       return new TransfersElevenAVinte();
        if (dias <= 30)       return new TransferesOneThirty();
        if (dias <= 40)       return new TransfersthirtyOneForty();
        if (dias <= 50)       return new TransfersFortyOneFifty();
        return new TransfersInvad();
    }
}
