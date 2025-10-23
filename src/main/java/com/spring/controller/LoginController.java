package com.spring.controller;

import com.spring.dto.LoginRequest;
import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController (UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequest request){
        try {
            User user = userService.authenticate(request.getEmail(),request.getPassword());
            return ResponseEntity.ok( new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getRole(), user.getActive()));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
