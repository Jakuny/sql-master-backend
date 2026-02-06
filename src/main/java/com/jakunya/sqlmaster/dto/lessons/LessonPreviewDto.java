package com.jakunya.sqlmaster.dto.lessons;

import lombok.Data;

@Data
public class LessonPreviewDto {
    private long id;
    private String title;
    private int seasonXp;
    private int orderIndex;
}
