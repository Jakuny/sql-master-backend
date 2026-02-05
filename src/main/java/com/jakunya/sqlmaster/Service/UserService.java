package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.dto.jwt.JwtAuthDto;
import com.jakunya.sqlmaster.dto.jwt.RefreshTokenDto;
import com.jakunya.sqlmaster.dto.user.UpdateUserDto;
import com.jakunya.sqlmaster.dto.user.UserCredDto;
import com.jakunya.sqlmaster.dto.user.UserProfileDto;
import com.jakunya.sqlmaster.dto.user.UserResponseDto;
import com.jakunya.sqlmaster.model.User;
import com.jakunya.sqlmaster.repository.UserRepository;
import com.jakunya.sqlmaster.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    final UserRepository repository;
    private final LeaderboardService leaderboardService;


    public User findUserByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public JwtAuthDto signIn(UserCredDto loginDto) {
        User user = findByCred(loginDto);
        return jwtService.genJwtAuthToken(user.getEmail());
    }

    public JwtAuthDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String requestRefreshToken = refreshTokenDto.getRefreshToken();
        if (!jwtService.validateJwtToken(requestRefreshToken)) {
            throw new RuntimeException("Bad Token error");
        }
        String email = jwtService.getEmailFromToken(requestRefreshToken);
        return jwtService.refreshBaseToken(email, requestRefreshToken);
    }

    public String createUser(User user){
        repository.save(user);
        return "User saved successfully";
    }
    public Optional<User> findById(Long id){
            return repository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

    public List<UserResponseDto> findAllUsers() {
        List<User> usersFromDb = repository.findAll();
        List<UserResponseDto> dtos = usersFromDb.stream()
                .map(this::toDto)
                .collect(java.util.stream.Collectors.toList());
        if (!dtos.isEmpty()) {
            System.out.println("--- DEBUG [4]: Первая DTO-шка: " + dtos.get(0).getEmail());
        }

        return dtos;
    }

    public String updateUser(User user){
        repository.save(user);
        return "User updated successfully";
    }


    @Transactional
    public String deleteUser(Long id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            repository.delete(userOptional.get());
            return "User deleted successfully";
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }
    }

    public String register(UserCredDto userCredDto) {
        if (repository.existsByEmail(userCredDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This email is already in use by another account.");
        } else if (repository.existsByUsername(userCredDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is used");
        } else {
            User user = new User();
            user.setEmail(userCredDto.getEmail());
            user.setUsername(userCredDto.getUsername());
            user.setPassword(passwordEncoder.encode(userCredDto.getPassword()));
            user.setRole("USER");
            user.setXp(0);
            repository.save(user);
            return "User successfully registered";
        }
    }

    public User findByCred(UserCredDto userCred) throws BadCredentialsException {
        Optional<User> optionalUser = repository.findByEmail(userCred.getEmail());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (passwordEncoder.matches(userCred.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new BadCredentialsException("Email or password is not correct");
    }

    private UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setXp(user.getXp());
        dto.setLvl(user.getLvl());
        dto.setDaysStreak(user.getDaysStreak());
        dto.setAvatarUrl(user.getAvatarUrl());
        return dto;
    }

    private UserProfileDto userToProfile(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.setUsername(user.getUsername());
        dto.setXp(user.getXp());
        dto.setLvl(user.getLvl());
        dto.setDaysStreak(user.getDaysStreak());
        dto.setAvatarUrl(user.getAvatarUrl());
        return dto;
    }

    public UserProfileDto searchProfileById(Long id) {
        User user = findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return userToProfile(user);
    }

    public UserProfileDto getMe(String email) {
        User user = findUserByEmail(email);
        return userToProfile(user);
    }

    public UserProfileDto updateUserProfile(String email, UpdateUserDto dto) {
        User userToUpdate = findUserByEmail(email);

        String oldUsername = userToUpdate.getUsername();
        String newUsername = dto.getUsername();
        if (newUsername == null || newUsername.isEmpty() || newUsername.equals(oldUsername)) {
            return userToProfile(userToUpdate);
        }
        if (repository.existsByUsername(newUsername)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is used");
        }
        userToUpdate.setUsername(newUsername);
        repository.save(userToUpdate);
        return userToProfile(userToUpdate);
    }

    public void updateUserAvatar(String email, String path) {
        User user = findUserByEmail(email);
        user.setAvatarUrl(path);
        repository.save(user);
    }

    public void addExp(User user, int amount) {
        user.addXp(amount);
        repository.save(user);
        leaderboardService.updateUserScore(user.getUsername(), (double) user.getXp());
    }

}
