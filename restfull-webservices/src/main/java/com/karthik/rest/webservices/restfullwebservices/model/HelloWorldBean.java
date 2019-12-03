package com.karthik.rest.webservices.restfullwebservices.model;

public class HelloWorldBean {

    public String getMessage() {
        return message;
    }

    public HelloWorldBean(String message) {
        this.message = message;
    }

    private String message;


}
