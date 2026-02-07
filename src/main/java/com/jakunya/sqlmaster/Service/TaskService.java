package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.CustomClass.Difficulty;
import com.jakunya.sqlmaster.CustomClass.TaskType;
import com.jakunya.sqlmaster.dto.task.TaskDetailDto;
import com.jakunya.sqlmaster.dto.task.TaskPreviewDto;
import com.jakunya.sqlmaster.dto.task.TaskRequestDto;
import com.jakunya.sqlmaster.model.Task;
import com.jakunya.sqlmaster.model.User;
import com.jakunya.sqlmaster.repository.TaskRepository;
import com.jakunya.sqlmaster.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SandBoxService sandBoxService;

    public Task correctGetTaskById(Long id) {
         return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskDetailDto toDtoDet(Task task) {
        TaskDetailDto dto = new TaskDetailDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        if(task.getType() == TaskType.SCRAMBLE) {
            List<String>scrambleWords = new java.util.ArrayList<>(Arrays.stream(task.getCorrectQuery()
                            .split("(?=[,.!;])|(?<=[,.!;])|\\s+"))
                    .toList());
            Collections.shuffle(scrambleWords);
            dto.setScrambleWords(scrambleWords);
            return dto;
        } else {
            dto.setDifficulty(task.getDifficulty());
            dto.setXpReward(task.getXpReward());
            dto.setInitScript(task.getInitScript());
            return dto;
        }
    }

    public TaskPreviewDto toDtoPre(Task task) {
        TaskPreviewDto dto = new TaskPreviewDto();
        dto.setDifficulty((Difficulty) task.getDifficulty());
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setXpReward(task.getXpReward());
        return dto;
    }

    public List<TaskPreviewDto> getAllTasks() {
        List<Task> taskDB = repository.findAllByLessonIsNull();
        List<TaskPreviewDto> dtos = taskDB.stream()
                .map(this::toDtoPre)
                .collect(toList());
        if (dtos.isEmpty()) {
            System.out.println("Task is empty");
        }

        return dtos;

    }

    public TaskDetailDto getTaskById(long id) {
        return toDtoDet(correctGetTaskById(id));
    }

    @Transactional
    public String checkTask(Long id, String userQuery, String email) {
        Task task = correctGetTaskById(id);
        User user = userService.findUserByEmail(email);
        boolean query = sandBoxService.compareResults(task.getInitScript(), userQuery, task.getCorrectQuery());
        if (!query) {
            return ("result:" + false);
        } else if ((user.getSolvedTasks().contains(task))) {
            return ("result:" + true);
        } else {
            userService.addExp(user, task.getXpReward());
            userService.updateStreak(user);
            user.getSolvedTasks().add(task);
            user.setLastCorrectTask(LocalDate.now());
            System.out.println("--- DEBUG SAVE: Пытаюсь сохранить юзера: " + user.getEmail());
            System.out.println("--- DEBUG SAVE: Дата для записи: " + user.getLastCorrectTask());
            userRepository.save(user);
            return ("result:" + true + ". added "  + task.getXpReward() + "xp" );
        }
    }

    public String createTask(TaskRequestDto dto){
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDifficulty(dto.getDifficulty());
        task.setXpReward(dto.getXpReward());
        task.setInitScript(dto.getInitScript());
        task.setCorrectQuery(dto.getCorrectQuery());
        repository.save(task);
        return "Task saved";
    }

}
