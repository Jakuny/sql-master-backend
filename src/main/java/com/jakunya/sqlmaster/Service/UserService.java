package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.dto.JwtAuthDto;
import com.jakunya.sqlmaster.dto.UserCredDto;
import com.jakunya.sqlmaster.model.User;
import com.jakunya.sqlmaster.repository.UserRepository;
import com.jakunya.sqlmaster.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.remote.JMXAuthenticator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder; // Чтобы хешировать пароли (превращать "123" в "f83jd...")
    private final JwtService jwtService;           // Твой генератор токенов
    final UserRepository repository;
    private AuthenticationManager authenticationManager;

    // Метод входа (Login)
    public JwtAuthDto signIn(UserCredDto loginDto) {
        // 1. Спринг сам проверяет логин и пароль.
        // Если пароль неверный — он тут кинет ошибку и дальше код не пойдет.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        // 2. Если мы тут — значит пароль верный. Генерируем токены.
        // Вызываем твой метод из JwtService
        return jwtService.genJwtAuthToken(loginDto.getEmail());
    }

    public String createUser(User user){
        repository.save(user);
        return "User saved successfully";
    }

    public Optional<User> findById(Long id){
            return repository.findById(id);
    }

    public List<User> findAllUsers() {
        return repository.findAll();
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
}
