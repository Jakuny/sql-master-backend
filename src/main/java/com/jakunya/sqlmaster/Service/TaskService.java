package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.CustomClass.Difficulty;
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

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        dto.setDifficulty(task.getDifficulty());
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setXpReward(task.getXpReward());
        dto.setDescription(task.getDescription());
        dto.setInitScript(task.getInitScript());
        return dto;
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
        List<Task> taskDB = repository.findAll();
        List<TaskPreviewDto> dtos = taskDB.stream()
                .map(this::toDtoPre)
                .collect(Collectors.toList());
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
