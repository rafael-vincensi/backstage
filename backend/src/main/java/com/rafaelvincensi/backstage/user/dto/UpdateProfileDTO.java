package com.rafaelvincensi.backstage.user.dto;

public record UpdateProfileDTO(
        String username,
        String name,
        String bio,
        String avatarUrl
) {
}
