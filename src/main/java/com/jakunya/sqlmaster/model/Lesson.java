package com.jakunya.sqlmaster.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private int orderIndex;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToMany(mappedBy = "lesson")
    private List<Task> tasks;
    private int seasonXp;
}
