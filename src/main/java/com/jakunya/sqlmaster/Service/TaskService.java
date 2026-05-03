    package com.jakunya.sqlmaster.Service;

    import com.jakunya.sqlmaster.CustomClass.Difficulty;
    import com.jakunya.sqlmaster.CustomClass.TaskType;
    import com.jakunya.sqlmaster.dto.task.TaskDetailDto;
    import com.jakunya.sqlmaster.dto.task.TaskPreviewDto;
    import com.jakunya.sqlmaster.dto.task.TaskRequestDto;
    import com.jakunya.sqlmaster.model.Lesson;
    import com.jakunya.sqlmaster.model.LessonProgress;
    import com.jakunya.sqlmaster.model.Task;
    import com.jakunya.sqlmaster.model.User;
    import com.jakunya.sqlmaster.repository.LessonProgressRepository;
    import com.jakunya.sqlmaster.repository.TaskRepository;
    import com.jakunya.sqlmaster.repository.UserRepository;
    import com.jakunya.sqlmaster.security.RateLimitFilter;
    import lombok.AllArgsConstructor;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.data.redis.core.StringRedisTemplate;

    import java.time.LocalDate;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.List;
    import java.time.Duration;
    import java.util.Locale;


    import static java.util.stream.Collectors.toList;

    @Service
    @AllArgsConstructor
    public class TaskService {
        private final TaskRepository repository;
        private final UserRepository userRepository;
        private final UserService userService;
        private final SandBoxService sandBoxService;
        private final LessonProgressRepository lessonProgressRepository;
        private static final String TASK_DATE_KEY = "cooldown";
        private final StringRedisTemplate redisTemplate;


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

        public TaskDetailDto getTaskById(long id) {
            return toDtoDet(correctGetTaskById(id));
        }

        @Transactional
        public String checkTask(Long id, String userQuery, String email) {
            String key = "cooldown:" + email;
            Boolean isAllowed = redisTemplate.opsForValue().setIfAbsent(
                    key,
                    "locked",
                    java.time.Duration.ofSeconds(3)
            );
            if (Boolean.FALSE.equals(isAllowed)) {
                throw new org.springframework.web.server.ResponseStatusException(
                        HttpStatus.TOO_MANY_REQUESTS, "Wait 3 seconds before trying to check task");
            }
            Task task = correctGetTaskById(id);
            User user = userService.findUserByEmail(email);
            if (task.getType() == TaskType.SQL) {
                boolean query = sandBoxService.compareResults(task.getInitScript(), userQuery, task.getCorrectQuery());
                if (!query) {
                    if (task.getLesson() == null) {
                        return ("result:" + false);
                    } else {
                        LessonProgress lessonProgress = getOrCreateTaskLessonProgress(user, task.getLesson());
                        lessonProgress.setMistakes_count(lessonProgress.getMistakes_count() + 1);
                        lessonProgressRepository.save(lessonProgress);
                        return ("result:" + false);
                    }
                } else if ((user.getSolvedTasks().contains(task))) {
                    return ("result:" + true);
                } else {
                    userService.addExp(user, task.getXpReward());
                    userService.updateStreak(user);
                    user.getSolvedTasks().add(task);
                    user.setLastCorrectTask(LocalDate.now());
                    userService.updateUser(user);
                    return ("result:" + true + ". added " + task.getXpReward() + "xp");
                }
            } else if (task.getType() == TaskType.SCRAMBLE) {
                String normalizedCorrect = task.getCorrectQuery().replaceAll("\\s+", "").toLowerCase();
                String normalizedUser = userQuery.replaceAll("\\s+", "").toLowerCase();

                if (normalizedCorrect.equals(normalizedUser)) {
                    user.getSolvedTasks().add(task);
                    userService.updateUser(user);
                    return ("result:" + true);
                }else if ((user.getSolvedTasks().contains(task))) {
                    return ("result:" + true);
                } else {
                    LessonProgress lessonProgress = getOrCreateTaskLessonProgress(user, task.getLesson());
                    lessonProgress.setMistakes_count(lessonProgress.getMistakes_count() + 1);
                    lessonProgressRepository.save(lessonProgress);
                    return ("result:" + false);
                }

            } else { throw new RuntimeException("Unknown task type");
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
