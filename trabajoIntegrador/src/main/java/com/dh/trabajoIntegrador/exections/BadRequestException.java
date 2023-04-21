package com.dh.trabajoIntegrador.exections;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }
}
