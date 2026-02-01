package com.jakunya.sqlmaster.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private int xp;
    private int lvl;
    private int daysStreak;
    private String avatarUrl;
}