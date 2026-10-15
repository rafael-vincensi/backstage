package com.rafaelvincensi.backstage.user.dto;

public record UpdateProfileRequest (
        String username,
        String name,
        String avatarUrl,
        String bio
){
}
