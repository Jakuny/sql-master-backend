package com.jakunya.sqlmaster.dto.task;

import com.jakunya.sqlmaster.CustomClass.Difficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class TaskDetailDto {
    private long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private int xpReward;
    private String initScript;
    private List<String> scrambleWords;
}
