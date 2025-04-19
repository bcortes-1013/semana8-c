package com.semana3.microservicio_viaje.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ViajeNotFoundException extends RuntimeException {
    public ViajeNotFoundException(String mensaje) {
        super(mensaje);
    }
}