package com.ecommerce.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standardized API response wrapper.
 * 
 * All API responses follow this format for consistency.
 * 
 * @author API Team
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String error;
    private int statusCode;
    private LocalDateTime timestamp;
    private String path;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message, String error, int statusCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
