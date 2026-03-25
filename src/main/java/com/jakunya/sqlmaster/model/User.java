package com.jakunya.sqlmaster.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    @Size(min = 3, max = 15, message = "Username must be at least 3-15 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Никнейм может содержать только буквы, цифры и _")
    private String username;
    @Email(message = "Please provide a valid email address")
    @Column(unique = true, nullable = false)
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(length = 512)
    private String avatarUrl;
    private String role;
    private int xp = 0;
    private int lvl = 0;
    private int daysStreak = 0;
    private LocalDate lastCorrectTask;
    private LocalDate lastActivity;
    @ManyToMany
    @JoinTable(
            name = "user_solved_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> solvedTasks = new java.util.HashSet<>();

    public void addXp(int amount){
        this.xp += amount;
    }

    public int getLvl() {
        lvl = this.xp/100;
        return lvl;
    }
}
