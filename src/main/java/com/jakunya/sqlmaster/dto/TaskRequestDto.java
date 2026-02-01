package com.jakunya.sqlmaster.dto;

import com.jakunya.sqlmaster.CustomClass.Difficulty; 
import lombok.Data;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private Difficulty difficulty;
    private int xpReward;
    private String correctQuery;
    private String initScript;
}
