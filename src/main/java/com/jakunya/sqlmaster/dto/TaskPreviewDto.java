package com.jakunya.sqlmaster.dto;

import com.jakunya.sqlmaster.CustomClass.Difficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TaskPreviewDto {
    private long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private int xpReward;
}
