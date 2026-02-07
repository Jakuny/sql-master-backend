package com.jakunya.sqlmaster.controller;

import com.jakunya.sqlmaster.Service.LessonService;
import com.jakunya.sqlmaster.dto.lessons.LessonCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson")
public class LessonController {
    private final LessonService service;

    @PostMapping
    public String saveTask(@RequestBody LessonCreateDto dto) {
        return service.createLesson(dto);
    }
}
