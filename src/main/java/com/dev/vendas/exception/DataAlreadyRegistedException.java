package com.dev.vendas.exception;

public class DataAlreadyRegistedException extends RuntimeException {
    public DataAlreadyRegistedException(String message) {
        super(message);
    }

}
