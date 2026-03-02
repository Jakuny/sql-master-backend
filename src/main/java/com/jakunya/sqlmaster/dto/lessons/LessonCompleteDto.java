package com.jakunya.sqlmaster.dto.lessons;

import lombok.Data;

@Data
public class LessonResultDto {
    private int stars;
    private int xpEarned;
    private boolean isFirstTime;
}