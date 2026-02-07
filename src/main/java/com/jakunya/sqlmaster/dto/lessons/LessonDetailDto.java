package com.jakunya.sqlmaster.dto.lessons;

import com.jakunya.sqlmaster.dto.task.TaskDetailDto;
import lombok.Data;

import java.util.List;

@Data
public class LessonDetailDto {
    private long id;
    private String title;
    private int seasonXp;
    private int orderIndex;
    private String content;
    List<TaskDetailDto> taskDetailDtos;
}
