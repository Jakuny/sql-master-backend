package com.jakunya.sqlmaster.repository;

import com.jakunya.sqlmaster.model.Lesson;
import com.jakunya.sqlmaster.model.LessonProgress;
import com.jakunya.sqlmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {
    Optional<LessonProgress> findByUserAndLesson(User user, Lesson lesson);
    List<LessonProgress> findAllByUserId(Long userId);


}