package com.rafaelvincensi.backstage.user;

import com.rafaelvincensi.backstage.exceptions.ConflictException;
import com.rafaelvincensi.backstage.exceptions.NotFoundException;
import com.rafaelvincensi.backstage.user.dto.CreateUserRequestDTO;
import com.rafaelvincensi.backstage.user.dto.UpdateProfileDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequestDTO request) {

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

    public User updateProfile(UUID id, UpdateProfileDTO request){

         var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));

         if (request.username() != null){
             userRepository.findByUsername(request.username())
                     .ifPresent(existingUser -> {
                         if (!user.getId().equals(existingUser.getId())) {
                             throw new ConflictException("Username already exists!");
                         }
                     });
             user.setUsername(request.username());
         }

         if (request.name() != null) user.setName(request.name());
         if (request.bio() != null) user.setBio(request.bio());
         if (request.avatarUrl() != null) user.setAvatarUrl(request.avatarUrl());
         user.setUpdatedAt(LocalDateTime.now());

         return userRepository.save(user);
    }
}
