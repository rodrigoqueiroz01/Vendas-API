package com.dev.vendas.api.exception;

public class DataAlreadyRegisteredException extends RuntimeException {

    public DataAlreadyRegisteredException(String message) {
        super(message);
    }
}
