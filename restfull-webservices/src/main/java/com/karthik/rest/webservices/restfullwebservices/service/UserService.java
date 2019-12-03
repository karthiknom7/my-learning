package com.karthik.rest.webservices.restfullwebservices.service;

import com.karthik.rest.webservices.restfullwebservices.exception.UserNotFoundExcGeption;
import com.karthik.rest.webservices.restfullwebservices.model.User;
import com.karthik.rest.webservices.restfullwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User findUser(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new UserNotFoundExcGeption("User not found");

        }
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
