package com.jakunya.sqlmaster.dto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDto {
    @Size(min = 3, max = 15, message = "Username must be at least 3-15 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Никнейм может содержать только буквы, цифры и _")
    String username;
}
