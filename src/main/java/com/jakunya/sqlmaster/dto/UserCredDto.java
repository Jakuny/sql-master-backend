package com.jakunya.sqlmaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCredDto {
    @NotBlank
    @Size(min = 3, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;
}