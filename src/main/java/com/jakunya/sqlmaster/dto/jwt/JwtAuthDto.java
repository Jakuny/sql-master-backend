package com.jakunya.sqlmaster.dto.jwt;

import lombok.Data;

@Data
public class JwtAuthDto {
    private String token;
    private String refreshToken;
}
