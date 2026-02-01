package com.jakunya.sqlmaster.model;


import com.jakunya.sqlmaster.repository.UserRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;
    @Email(message = "Please provide a valid email address")
    @Column(unique = true, nullable = false)
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank
    @Column(nullable = false)
    private String password;
    private String role;
    private int xp = 0;
    @Transient
    private int lvl;
    private int daysStreak = 0;
    public int getLvl() {
        return this.xp/1000;
    }


}
