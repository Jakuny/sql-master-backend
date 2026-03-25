 package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.CustomClass.TaskType;
import com.jakunya.sqlmaster.dto.lessons.*;
import com.jakunya.sqlmaster.dto.task.TaskDetailDto;
import com.jakunya.sqlmaster.dto.task.TaskRequestDto;
import com.jakunya.sqlmaster.model.Lesson;
import com.jakunya.sqlmaster.model.LessonProgress;
import com.jakunya.sqlmaster.model.Task;
import com.jakunya.sqlmaster.model.User;
import com.jakunya.sqlmaster.repository.LessonProgressRepository;
import com.jakunya.sqlmaster.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

 @Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;
    private final TaskService taskService;
    private final LessonProgressRepository lessonProgressRepository;
    private final UserService userService;

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


    public LessonPreviewDto toPreviewDto(Lesson lesson, LessonProgress lessonProgress) {
        LessonPreviewDto dto = new LessonPreviewDto();
        if (lessonProgress != null){
            dto.setStars(lessonProgress.getStars());
            dto.setCompleted(lessonProgress.isCompleted());
        } else {
            dto.setStars(0);
            dto.setCompleted(false);
        }
        dto.setId(lesson.getId());
        dto.setOrderIndex(lesson.getOrderIndex());
        dto.setTitle(lesson.getTitle());
        dto.setSeasonXp(lesson.getSeasonXp());
        return dto;
    }

    public List<LessonPreviewDto> getAllLessons(String email){
        List<Lesson> lessons = repository.findAll();
        User user = userService.findUserByEmail(email);
        List<LessonProgress> progressList = lessonProgressRepository.findAllByUserId(user.getId());
        List<LessonPreviewDto> dtos = getAllLessonsMethod(progressList,lessons);
        return dtos;
    }

    public List<LessonPreviewDto> getAllLessonsMethod( List<LessonProgress> progressList, List<Lesson> lessons){
        List<LessonPreviewDto> dtos = new ArrayList<>();
        for (Lesson lesson : lessons){
            LessonProgress progress = null;
            for (LessonProgress p : progressList){
                if(p.getLesson().getId().equals(lesson.getId())){
                    progress = p;
                    break;
                }
            }
            dtos.add(toPreviewDto(lesson, progress));
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

    public LessonProgress getLessonProgress(User user, Lesson lesson){
        return lessonProgressRepository.findByUserAndLesson(user, lesson)
                .orElseThrow(() -> new RuntimeException("Lesson Progress Not Found"));
    }

    public LessonProgressDto getLessonProgressDto(User user, Lesson lesson){
        LessonProgress progress = getLessonProgress(user, lesson);
        LessonProgressDto dto = new LessonProgressDto();
        dto.setCompleted(progress.isCompleted());
        dto.setStars(progress.getStars());
        return dto;
    }

     public LessonProgress getOrCreateTaskLessonProgress(User user, Lesson lesson){
         return lessonProgressRepository.findByUserAndLesson(user, lesson)
                 .orElseGet(() -> {
                     LessonProgress lessonProgress = new LessonProgress();
                     lessonProgress.setLesson(lesson);
                     lessonProgress.setUser(user);
                     lessonProgress.setMistakes_count(0);
                     lessonProgress.setCompleted(false);
                     lessonProgress.setStars(0);
                     lessonProgressRepository.save(lessonProgress);
                     return lessonProgress;
                 });
     }

    public LessonCompleteDto completeLesson(long id, String email){
        User user = userService.findUserByEmail(email);
        Lesson lesson = repository.getLessonById(id);
        LessonProgress progress = getOrCreateTaskLessonProgress(user, lesson);
        List<Task> tasks = lesson.getTasks();
        Set<Task> userTasks = user.getSolvedTasks();
        for (Task task : tasks) {
            if (!userTasks.contains(task)){
                throw new RuntimeException("You dont complete all Tasks");
            }
        }


        int mistakes = progress.getMistakes_count();
        int stars = 0;

        if (mistakes == 0){
            stars += 3;
        } else if (mistakes >= 1 && 3 > mistakes){
            stars += 2;
        } else {
            stars += 1;
        }

        boolean isFirstTime = !progress.isCompleted();
        int actualXpEarned = 0;

        if (isFirstTime){
            actualXpEarned = lesson.getSeasonXp();
            userService.addExp(user, actualXpEarned);
        }
        userService.updateStreak(user);
        user.setLastCorrectTask(LocalDate.now());
        userService.updateUser(user);


        if (stars > progress.getStars()){
            progress.setStars(stars);
        }
        progress.setCompleted(true);
        lessonProgressRepository.save(progress);


        LessonCompleteDto lessonCompleteDto = new LessonCompleteDto();
        lessonCompleteDto.setXpEarned(actualXpEarned);
        lessonCompleteDto.setStars(stars);
        lessonCompleteDto.setFirstTime(isFirstTime);
        return lessonCompleteDto;
    }

    public void saveProgress(LessonProgress progress){
        lessonProgressRepository.save(progress);
    }
}
