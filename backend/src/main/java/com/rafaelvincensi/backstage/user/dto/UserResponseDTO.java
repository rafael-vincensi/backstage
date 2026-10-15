package com.rafaelvincensi.backstage.user.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String name,
        String bio
){
}
