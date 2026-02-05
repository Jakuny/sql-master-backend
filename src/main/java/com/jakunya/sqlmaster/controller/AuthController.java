package com.jakunya.sqlmaster.controller;


import com.jakunya.sqlmaster.Service.UserService;
import com.jakunya.sqlmaster.dto.jwt.JwtAuthDto;
import com.jakunya.sqlmaster.dto.jwt.RefreshTokenDto;
import com.jakunya.sqlmaster.dto.user.UserCredDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sing-in")
    public ResponseEntity<JwtAuthDto> singIn(@RequestBody UserCredDto userCredDto) {
        try {
            JwtAuthDto jwtAuthDto = userService.signIn(userCredDto);
            return ResponseEntity.ok(jwtAuthDto);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Auth failed: "+ e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserCredDto userCredDto) {
        String result = userService.register(userCredDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh")
    public JwtAuthDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        return userService.refreshToken(refreshTokenDto);
    }
}