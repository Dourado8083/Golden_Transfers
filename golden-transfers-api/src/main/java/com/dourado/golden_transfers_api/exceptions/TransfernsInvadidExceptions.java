package com.dourado.golden_transfers_api.exceptions;

public class TransfernsInvadidExceptions extends RuntimeException {
    public TransfernsInvadidExceptions(Long id) {
        super("Transferência não encontrada com o id: " + id);
    }
}