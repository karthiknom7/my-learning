package com.karthik.rest.webservices.restfullwebservices.controller;

import com.karthik.rest.webservices.restfullwebservices.model.User;
import com.karthik.rest.webservices.restfullwebservices.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users/{id}")
    public Resource<User> getUser(@PathVariable int id){
        log.info("Request for user {}", id);
        User user = userService.findUser(id);
        Resource<User> userResource = new Resource<>(user);

        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
        userResource.add(linkTo.withRel("all-users"));
        return userResource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User savedUsed = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUsed.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }
}
