package com.ms.accounttransactions_back.exception;

/**
 * @author : Freddy Torres
 * file :  NotFoundException
 * @since : 3/4/2025, jue
 **/

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}