package com.karthik.rest.webservices.restfullwebservices.controller;

import com.karthik.rest.webservices.restfullwebservices.model.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "hello-world")
    public String getHelloWorld(){
        return "Hello World";

    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean getHelloWorldBean(){
        return new HelloWorldBean("Hello World bean");
    }
}
