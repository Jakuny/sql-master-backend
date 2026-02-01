package com.jakunya.sqlmaster.repository;

import com.jakunya.sqlmaster.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> getTaskById(long id);
}
