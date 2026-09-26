package com.nova.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        log.error("Runtime exception occurred: ", e);
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("status", "ERROR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Illegal argument exception: ", e);
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("status", "BAD_REQUEST");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}