package com.ibm.controller.exceptions;

import com.ibm.services.exceptions.ObjectNotFoundException;
import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String MAPPING_ERROR_MESSAGE = "An error ocurred while mapping the entity properties. Please, contact the system administrator";

    private final String HTTP_REQUEST_METHOD_NOT_SUPPORTED_MESSAGE = "HTTP method not supported for this operation. Please, try another method";

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<StandardError> mappingException(MappingException e, HttpServletRequest req) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Mapping Error",
                MAPPING_ERROR_MESSAGE, req.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> httpRequestNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Bad request",
                HTTP_REQUEST_METHOD_NOT_SUPPORTED_MESSAGE, req.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
