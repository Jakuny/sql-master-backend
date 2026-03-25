package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.dto.user.UpdateUserDto;
import com.jakunya.sqlmaster.model.User;
import com.jakunya.sqlmaster.repository.UserRepository;
import com.jakunya.sqlmaster.security.jwt.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private LeaderboardService leaderboardService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void updateStreak_ShouldIncreaseStreak_WhenSolvedYesterday() {
        User user = new User();
        user.setLastCorrectTask(LocalDate.now().minusDays(1));
        user.setDaysStreak(5);
        userService.updateStreak(user);
        Assertions.assertEquals(6, user.getDaysStreak(), "Days streak should be increased");
    }

    @Test
    void updateStreak_ShouldIncreaseStreak_ShouldResetStreak() {
        User user = new User();
        user.setLastCorrectTask(LocalDate.now().minusDays(3));
        user.setDaysStreak(5);
        userService.updateStreak(user);
        Assertions.assertEquals(1, user.getDaysStreak(), "Days streak should be reset");
    }

    @Test
    void addUserXp(){
        User user = new User();
        user.setUsername("jakunya");
        user.setXp(0);
        userService.addExp(user, 20);
        double xp = user.getXp();
        Assertions.assertEquals(20, user.getXp(), "Xp should be increased");
        Mockito.verify(userRepository).save(user);
        Mockito.verify(leaderboardService).updateUserScore(user.getUsername(), xp );
    }

    @Test
    void updateUsername(){
        User user = new User();
        user.setUsername("jakunya");
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setUsername("jakuny");
        Mockito.when(userRepository.findByEmail("test@mail.ru")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsByUsername(updateUserDto.getUsername())).thenReturn(false);
        userService.updateUserProfile("test@mail.ru", updateUserDto);
        Assertions.assertEquals(updateUserDto.getUsername(), user.getUsername(), "Username should be updated");
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void throwUpdateUserException(){
        User user = new User();
        user.setUsername("jakunya");
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setUsername("jakunya3310");
        Mockito.when(userRepository.findByEmail("test@mail.ru")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsByUsername(updateUserDto.getUsername())).thenReturn(true);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, ()-> {
            userService.updateUserProfile("test@mail.ru", updateUserDto);
        });
        Mockito.verify(userRepository, Mockito.never()).save(user);
        Assertions.assertEquals(HttpStatus.CONFLICT,exception.getStatusCode(), "Status code should be conflict");
    }

}
