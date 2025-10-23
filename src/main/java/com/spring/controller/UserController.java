package com.spring.controller;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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
        return new UserDTO(saved.getId(),saved.getName(),saved.getEmail(),user.getRole(), user.getActive());
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        return new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getRole(), user.getActive());
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id,@RequestBody User userData){
        User updated = userService.updateUser(id,userData);
        return new UserDTO(updated.getId(),updated.getName(),updated.getEmail(),updated.getRole(), updated.getActive());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès");
    }
}
