package com.rafaelvincensi.backstage.user;

import com.rafaelvincensi.backstage.user.dto.CreateUserRequestDTO;
import com.rafaelvincensi.backstage.user.dto.UpdateProfileRequestDTO;
import com.rafaelvincensi.backstage.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/users")
@RestController
public class UserController {

    private UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> findUserByUsername(@PathVariable String username){
        User userByUsername = userService.findUserByUsername(username);
        var response = UserResponseDTO.fromEntity(userByUsername);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable UUID id){
        User userById = userService.findUserById(id);
        var response = UserResponseDTO.fromEntity(userById);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO request){
        User savedUser = userService.createUser(request);
        var response = UserResponseDTO.fromEntity(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<UserResponseDTO> updateProfile(@Valid @RequestBody UpdateProfileRequestDTO request, @PathVariable UUID id){
        User user = userService.updateProfile(id, request);
        var response = UserResponseDTO.fromEntity(user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
         userService.deleteUser(id);

         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
