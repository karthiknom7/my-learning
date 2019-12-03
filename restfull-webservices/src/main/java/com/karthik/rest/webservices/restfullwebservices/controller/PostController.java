package com.karthik.rest.webservices.restfullwebservices.controller;

import com.karthik.rest.webservices.restfullwebservices.model.Post;
import com.karthik.rest.webservices.restfullwebservices.model.User;
import com.karthik.rest.webservices.restfullwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}/posts")
    public List<Post> getPosts(@PathVariable("id") Integer userId){
        User user = userService.findUser(userId);
        return user.getPosts();
    }
}
