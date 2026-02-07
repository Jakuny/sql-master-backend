package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.CustomClass.TaskType;
import com.jakunya.sqlmaster.dto.lessons.LessonCreateDto;
import com.jakunya.sqlmaster.dto.task.TaskRequestDto;
import com.jakunya.sqlmaster.model.Lesson;
import com.jakunya.sqlmaster.model.Task;
import com.jakunya.sqlmaster.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;

    @Transactional
    public String createLesson(LessonCreateDto dto){
        Lesson lesson = new Lesson();
        lesson.setTitle(dto.getTitle());
        lesson.setContent(dto.getContent());
        lesson.setOrderIndex(dto.getOrderIndex());
        lesson.setSeasonXp(dto.getSeasonXp());
        List<Task> tasks = new ArrayList<>();
        for (TaskRequestDto taskDto : dto.getTasks()) {
            Task task = new Task();
            task.setLesson(lesson);
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setType(taskDto.getType());
            task.setCorrectQuery(taskDto.getCorrectQuery());
            if (taskDto.getType() == TaskType.SQL) {
                task.setDifficulty(taskDto.getDifficulty());
                task.setInitScript(taskDto.getInitScript());
            }
            tasks.add(task);
        }
        lesson.setTasks(tasks);
        repository.save(lesson);
        return "Lesson saved";
    }
}
