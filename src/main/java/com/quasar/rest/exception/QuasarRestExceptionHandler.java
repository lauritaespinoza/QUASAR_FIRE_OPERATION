package com.quasar.rest.exception;

import com.quasar.service.exception.NotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class QuasarRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> HandlerNotFoundException(NotFoundException ex, WebRequest request){
        JsonNode payload = createExceptionJson(ex.getMessage(), HttpStatus.NOT_FOUND, request);
        return handleExceptionInternal(ex, payload, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private JsonNode createExceptionJson(String message, HttpStatus status, WebRequest request) {
        JsonNode json = JsonNodeFactory.instance.objectNode();
        ((ObjectNode) json).put("timestamp", Instant.now().toEpochMilli());
        ((ObjectNode) json).put("status", status.value());
        ((ObjectNode) json).put("error", status.name());
        ((ObjectNode) json).put("message", message);
        ((ObjectNode) json).put("path", request.getContextPath());
        return json;
    }
}
