package com.jakunya.sqlmaster.dto.task;

import com.jakunya.sqlmaster.CustomClass.Difficulty;
import com.jakunya.sqlmaster.CustomClass.TaskType;
import lombok.Data;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private Difficulty difficulty;
    private int xpReward;
    private String correctQuery;
    private String initScript;
    private TaskType type;
}
