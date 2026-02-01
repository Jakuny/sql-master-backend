package com.jakunya.sqlmaster.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private String username;
    private int xp;
    private int lvl;
    private int daysStreak;
    private String avatarUrl;
}
