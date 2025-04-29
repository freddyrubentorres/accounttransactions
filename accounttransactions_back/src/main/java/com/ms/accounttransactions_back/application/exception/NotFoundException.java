package com.ms.accounttransactions_back.application.exception;


/**
 * @author : Freddy Torres
 * file : NotFoundException
 * @since : 24/4/2025, jue
 **/

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
