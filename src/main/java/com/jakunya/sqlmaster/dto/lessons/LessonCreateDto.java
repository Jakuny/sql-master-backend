package com.jakunya.sqlmaster.dto.lessons;

import com.jakunya.sqlmaster.dto.task.TaskRequestDto;
import com.jakunya.sqlmaster.model.Task;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class LessonCreateDto {
    private String title;
    private int orderIndex;
    private String content;
    private List<TaskRequestDto> tasks;
    private int seasonXp;
}
