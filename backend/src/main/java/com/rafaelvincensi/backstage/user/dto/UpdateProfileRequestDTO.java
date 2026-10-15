package com.rafaelvincensi.backstage.user.dto;

public record UpdateProfileRequestDTO(
        String username,
        String name,
        String avatarUrl,
        String bio
){
}
