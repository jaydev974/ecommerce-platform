package com.ecommerce.exception;

/**
 * Exception thrown when invalid credentials are provided.
 * 
 * @author Error Handling Team
 * @version 1.0.0
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}

