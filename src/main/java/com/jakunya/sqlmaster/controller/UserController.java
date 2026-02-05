package com.jakunya.sqlmaster.controller;

import com.jakunya.sqlmaster.Service.UserService;
import com.jakunya.sqlmaster.dto.user.UserResponseDto;
import com.jakunya.sqlmaster.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        System.out.println("--- DEBUG [5]: Зашел в UserController.getAllUsers ---");
        List<UserResponseDto> response = service.findAllUsers();
        System.out.println("--- DEBUG [6]: Сервис вернул в контроллер " + response.size() + " DTO-шек. Отдаю в Spring...");
        return response;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
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
