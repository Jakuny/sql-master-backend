package com.jakunya.sqlmaster.controller;

import com.jakunya.sqlmaster.Service.LessonService;
import com.jakunya.sqlmaster.dto.lessons.LessonCreateDto;
import com.jakunya.sqlmaster.dto.lessons.LessonDetailDto;
import com.jakunya.sqlmaster.dto.lessons.LessonPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson")
public class LessonController {
    private final LessonService service;

    @PostMapping
    public String saveTask(@RequestBody LessonCreateDto dto) {
        return service.createLesson(dto);
    }

    @GetMapping
    public List<LessonPreviewDto> getAllLessons() {
        return service.getAllLessons();
    }

    @GetMapping
    public LessonDetailDto getLessonById(@PathVariable long id) {
        return service.getLessonById(id);
    }
}
