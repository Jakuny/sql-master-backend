package com.jakunya.sqlmaster.controller;

import com.jakunya.sqlmaster.Service.FileStorageService;
import com.jakunya.sqlmaster.Service.UserService;
import com.jakunya.sqlmaster.dto.user.UpdateUserDto;
import com.jakunya.sqlmaster.dto.user.UserProfileDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final UserService service;
    private final FileStorageService fileService;

    @GetMapping("/{id}")
    public ResponseEntity getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(service.searchProfileById(id));
    }

    @GetMapping("/me")
    public ResponseEntity getMe(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.ok(service.getMe(principal.getName()));
        }
    }

    @PatchMapping("/me")
    public ResponseEntity<UserProfileDto> updateProfile(@RequestBody @Valid UpdateUserDto dto, Principal principal){
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else { 
            return ResponseEntity.ok(service.updateUserProfile(principal.getName(), dto));
        }
    }


    @PostMapping("/me/avatar/upload")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        String path = fileService.saveAvatar(file);
        service.updateUserAvatar(principal.getName(), path);
        return ResponseEntity.ok(path);
    }

}
