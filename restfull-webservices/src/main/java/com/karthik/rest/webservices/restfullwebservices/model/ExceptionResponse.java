package com.karthik.rest.webservices.restfullwebservices.model;

import java.util.Date;

public class ExceptionResponse {

    private String message;
    private Date timestamp;
    private String details;

    public ExceptionResponse(String message, Date timestamp, String details) {
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }
}
