package com.rafaelvincensi.backstage.user.dto;

import com.rafaelvincensi.backstage.user.User;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String name,
        String bio
){
    public static UserResponseDTO fromEntity(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getBio()
        );
    }
}
