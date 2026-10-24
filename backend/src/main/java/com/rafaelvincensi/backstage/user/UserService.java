package com.rafaelvincensi.backstage.user;

import com.rafaelvincensi.backstage.exceptions.ConflictException;
import com.rafaelvincensi.backstage.exceptions.NotFoundException;
import com.rafaelvincensi.backstage.infra.S3StorageService;
import com.rafaelvincensi.backstage.user.dto.CreateUserRequestDTO;
import com.rafaelvincensi.backstage.user.dto.UpdateProfileRequestDTO;
import io.awspring.cloud.s3.S3Template;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3StorageService s3Service;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, S3StorageService s3Service) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.s3Service = s3Service;
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
        user.setEmail(request.email());
        user.setPassword(passwordHash);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User findUserById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    public User updateProfile(UUID id, UpdateProfileRequestDTO request, MultipartFile file){

         var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));

         if (request.username() != null && !request.username().isBlank()){
             userRepository.findByUsername(request.username())
                     .ifPresent(existingUser -> {
                         if (!user.getId().equals(existingUser.getId())) {
                             throw new ConflictException("Username already exists!");
                         }
                     });
             user.setUsername(request.username());
         }

         if (request.name() != null && !request.name().isBlank()) user.setName(request.name());
         if (request.bio() != null) user.setBio(request.bio());

         user.setUpdatedAt(LocalDateTime.now());

         return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID id){
        var user = userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User not found!"));

        userRepository.deleteById(id);
    }
}
