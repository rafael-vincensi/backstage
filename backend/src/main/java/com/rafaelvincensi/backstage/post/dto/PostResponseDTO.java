package com.rafaelvincensi.backstage.post.dto;

import com.rafaelvincensi.backstage.post.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostResponseDTO(
        UUID id,
        String imageUrl,
        LocalDateTime createdAt
) {
    public static PostResponseDTO fromEntity(Post post){
        return new PostResponseDTO(
                post.getId(),
                post.getImageUrl(),
                post.getCreatedAt()
        );
    }
}
