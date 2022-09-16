package com.dev.vendas.api.exception;

public class VendedorNotFoundException extends RuntimeException {

    public VendedorNotFoundException() {
        super("Vendedor não encontrado na base de dados!");
    }
    
}
