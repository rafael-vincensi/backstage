package com.rafaelvincensi.backstage.user;

import com.rafaelvincensi.backstage.user.dto.CreateUserRequestDTO;
import com.rafaelvincensi.backstage.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO request){
        User savedUser = userService.createUser(request);
        var response = UserResponseDTO.fromEntity(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
