package com.dourado.golden_transfers_api.strategy;

import java.util.List;
import org.springframework.stereotype.Component;

@Component 
public class TransfersStategyFactory {

    private final List<TransfersStrategy> strategies;

    public TransfersStategyFactory(List<TransfersStrategy> strategies) {
        this.strategies = strategies;
    }

    public TransfersStrategy getStrategy(long dias) {
        if (dias < 0) {
            return findStrategy(TransfersInvad.class);
        }
        if (dias == 0) {
            return findStrategy(TransfersZeroDayRate.class);
        }
        if (dias >= 1 && dias <= 10) {
            return findStrategy(TransfersRateOneToTen.class);
        }
        if (dias >= 11 && dias <= 20) {
            return findStrategy(TransfersElevenAVinte.class);
        }
        if (dias >= 21 && dias <= 30) {
            return findStrategy(TransferesOneThirty.class);
        }
        if (dias >= 31 && dias <= 40) {
            return findStrategy(TransfersthirtyOneForty.class);
        }
        if (dias >= 41 && dias <= 50) {
            return findStrategy(TransfersFortyOneFifty.class);
        }
        
        return findStrategy(TransfersInvad.class);
    }

    private TransfersStrategy findStrategy(Class<? extends TransfersStrategy> clazz) {
        return strategies.stream()
                .filter(clazz::isInstance)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estratégia não encontrada para a classe: " + clazz.getSimpleName()));
    }
}