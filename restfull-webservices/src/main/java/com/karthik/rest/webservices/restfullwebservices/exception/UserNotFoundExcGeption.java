package com.karthik.rest.webservices.restfullwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundExcGeption extends RuntimeException {

    private String message;

    public UserNotFoundExcGeption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
