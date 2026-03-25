package com.jakunya.sqlmaster.dto.lessons;

import lombok.Data;

@Data
public class LessonProgressDto {
    private int stars;
    private boolean isCompleted;
}
