package com.rafaelvincensi.backstage.user;

import com.rafaelvincensi.backstage.exceptions.ConflictException;
import com.rafaelvincensi.backstage.user.dto.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request) {

        userRepository.findByUsername(request.username())
                .ifPresent(user -> {
                    throw new ConflictException("User already exists!");
                });

        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new ConflictException("Email already exists!");
                });

        var passwordHash = passwordEncoder.encode(request.password());

        User user = new User();
        user.setUsername(request.username());
        user.setName(request.name());
        user.setPassword(passwordHash);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}
