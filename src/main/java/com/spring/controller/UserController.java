package com.spring.controller;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.findAllDto();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody User user){
        User saved = userService.save(user);
        return new UserDTO(saved.getId(),saved.getName(),saved.getEmail());
    }

    @GetMapping("/id")
    public UserDTO getUSerById(@PathVariable Long id){
        User user = userService.findById(id);
        return new UserDTO(user.getId(),user.getName(),user.getEmail());
    }
}
