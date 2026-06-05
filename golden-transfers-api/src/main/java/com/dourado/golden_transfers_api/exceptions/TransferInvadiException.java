package com.dourado.golden_transfers_api.exceptions;

public class TransferInvadiException extends RuntimeException {
    public TransferInvadiException(long dias) {
        super("Nenhuma taxa aplicável para transferências com prazo de " + dias + " dias.");
    }
}