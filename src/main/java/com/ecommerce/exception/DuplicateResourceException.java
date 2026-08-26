package com.ecommerce.exception;

/**
 * Exception thrown when attempting to create a duplicate resource.
 * 
 * @author Error Handling Team
 * @version 1.0.0
 */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}

