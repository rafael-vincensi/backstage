package com.rafaelvincensi.backstage.user.dto;

public record CreateUserRequestDTO(
        String username,
        String name,
        String email,
        String password
) {
}
