package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.CustomClass.TaskType;
import com.jakunya.sqlmaster.dto.lessons.LessonCreateDto;
import com.jakunya.sqlmaster.dto.lessons.LessonDetailDto;
import com.jakunya.sqlmaster.dto.lessons.LessonPreviewDto;
import com.jakunya.sqlmaster.dto.task.TaskDetailDto;
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
    private final TaskService taskService;

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

    public LessonPreviewDto toPreviewDto(Lesson lesson) {
        LessonPreviewDto dto = new LessonPreviewDto();
        dto.setId(lesson.getId());
        dto.setOrderIndex(lesson.getOrderIndex());
        dto.setTitle(lesson.getTitle());
        dto.setSeasonXp(lesson.getSeasonXp());
        return dto;
    }

    public List<LessonPreviewDto> getAllLessons(){
        List<Lesson> lessons = repository.findAll();
        List<LessonPreviewDto> dtos = new ArrayList<>();

        for (Lesson lesson: lessons) {
            LessonPreviewDto dto = new LessonPreviewDto();
            dto.setId(lesson.getId());
            dto.setTitle(lesson.getTitle());
            dto.setSeasonXp(lesson.getSeasonXp());
            dto.setOrderIndex(dto.getOrderIndex());
            dtos.add(dto);
        }
        return dtos;
    }

    public LessonDetailDto toDetailDto(Lesson lesson) {
        LessonDetailDto dto = new LessonDetailDto();
        dto.setId(lesson.getId());
        dto.setTitle(lesson.getTitle());
        dto.setContent(lesson.getContent());
        dto.setSeasonXp(lesson.getSeasonXp());
        dto.setOrderIndex(lesson.getOrderIndex());

        List<TaskDetailDto> tasks = new ArrayList<>();

        for (int i = 0; i < lesson.getTasks().size(); i++) {
            tasks.add(taskService.toDtoDet(lesson.getTasks().get(i)));
        }
        dto.setTaskDetailDtos(tasks);
        return dto;
    }

    public LessonDetailDto getLessonById(long id) {
        Lesson lesson = repository.getLessonById(id);
        return toDetailDto(lesson);
    }
}
