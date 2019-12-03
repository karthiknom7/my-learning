package com.karthik.rest.webservices.restfullwebservices.controller;

import com.karthik.rest.webservices.restfullwebservices.exception.UserNotFoundExcGeption;
import com.karthik.rest.webservices.restfullwebservices.model.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
        return createResponeEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFoundExcGeption.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotException(UserNotFoundExcGeption ex, WebRequest request) throws Exception {
        return createResponeEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return createResponeEntity(ex, request, HttpStatus.BAD_REQUEST, "Validation failed exception");
    }

    private ResponseEntity<ExceptionResponse> createResponeEntity(Exception ex, WebRequest request, HttpStatus httpStatus){
       return createResponeEntity(ex, request, httpStatus, ex.getMessage());
    }


    private ResponseEntity<ExceptionResponse> createResponeEntity(Exception ex, WebRequest request, HttpStatus httpStatus, String message){
        ExceptionResponse exceptionResponse = new ExceptionResponse(message, new Date(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, httpStatus);
    }
}
