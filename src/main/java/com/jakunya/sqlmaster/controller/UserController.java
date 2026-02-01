package com.jakunya.sqlmaster.controller;

import com.jakunya.sqlmaster.Service.UserService;
import com.jakunya.sqlmaster.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    @GetMapping
    public List<User> getAllUsers(){
        return  service.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping
    public String createUser(@Valid @RequestBody User user){
        return service.createUser(user);
    }

    @PutMapping
    public String updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

}
