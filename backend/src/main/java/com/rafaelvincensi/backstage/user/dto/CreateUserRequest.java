package com.rafaelvincensi.backstage.user.dto;

public record CreateUserRequest (
        String username,
        String name,
        String email,
        String password
) {
}
