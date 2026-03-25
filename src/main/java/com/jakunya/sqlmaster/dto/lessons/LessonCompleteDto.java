package com.jakunya.sqlmaster.dto.lessons;

import lombok.Data;

@Data
public class LessonCompleteDto {
    private int stars;
    private int xpEarned;
    private boolean isFirstTime;
}