package com.jakunya.sqlmaster.model;

import com.jakunya.sqlmaster.CustomClass.Difficulty;
import com.jakunya.sqlmaster.CustomClass.TaskType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tasks")
public class Task {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private long id;
    private String title;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private int xpReward;
    @Column(name = "correct_query",length = 2000)
    private String correctQuery;
    @Column(name = "init_Script",length = 2000)
    private String initScript;
    private TaskType type;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    @ManyToMany
    @JoinTable(
            name = "user_solved_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> solvedTasks = new java.util.HashSet<>();
}
