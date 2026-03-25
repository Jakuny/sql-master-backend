package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.dto.user.UserCredDto;
import com.jakunya.sqlmaster.model.User;
import com.jakunya.sqlmaster.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Test
    void realCreateUser() {
        UserCredDto dto = new UserCredDto();
        dto.setUsername("admin");
        dto.setPassword("admin");
        dto.setEmail("admin@admin");
        userService.register(dto);
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Assertions.assertNotEquals(user.getPassword(), dto.getPassword());
        Assertions.assertEquals(user.getEmail(), dto.getEmail());
        Assertions.assertEquals(user.getUsername(), dto.getUsername());
        Assertions.assertEquals(0, user.getXp());
    }

    @Test
    void register_ShouldThrowException_WhenEmailIsAlreadyExists() {
        UserCredDto dto = new UserCredDto();
        dto.setUsername("admin");
        dto.setPassword("admin");
        dto.setEmail("admin@admin");
        userService.register(dto);
        UserCredDto dto2 = new UserCredDto();
        dto2.setUsername("admin2");
        dto2.setPassword("admin2");
        dto2.setEmail("admin@admin");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, ()-> {
            userService.register(dto2);
        });
        Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }
}
