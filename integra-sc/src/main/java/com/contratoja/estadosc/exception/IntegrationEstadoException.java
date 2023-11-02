package com.contratoja.estadosc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IntegrationEstadoException extends Exception {

    private final HttpStatus status;

    public IntegrationEstadoException(HttpStatus status, String message, Object... args) {
        super(String.format(message, args), null);
        this.status = status;
    }
}
