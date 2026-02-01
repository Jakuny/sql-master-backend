package com.jakunya.sqlmaster.dto;

import lombok.Data;

@Data
public class JwtAuthDto {
    private String token;
    private String refreshToken;
}
