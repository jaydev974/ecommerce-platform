package com.ecommerce.exception;

/**
 * Exception thrown when an operation is not authorized.
 * 
 * @author Error Handling Team
 * @version 1.0.0
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}

