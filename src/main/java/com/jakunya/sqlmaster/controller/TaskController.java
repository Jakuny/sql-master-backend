package com.jakunya.sqlmaster.controller;

import com.jakunya.sqlmaster.Service.TaskService;
import com.jakunya.sqlmaster.dto.task.TaskDetailDto;
import com.jakunya.sqlmaster.dto.task.TaskPreviewDto;
import com.jakunya.sqlmaster.dto.task.TaskRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<TaskPreviewDto> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDetailDto getTaskById(@PathVariable long id){
        return service.getTaskById(id);
    }

    @PostMapping("/{id}")
    public String checkTask(@PathVariable long id, @RequestBody String userQuery, Principal principal) {
        return service.checkTask(id, userQuery, principal.getName());
    }

    @PostMapping
    public String createTask(@RequestBody TaskRequestDto dto){
        return service.createTask(dto);
    }
}
