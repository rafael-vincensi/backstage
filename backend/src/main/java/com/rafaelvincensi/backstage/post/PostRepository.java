package com.rafaelvincensi.backstage.post;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository<Post, UUID> {

    List<Post> findByUserId(UUID id);
}
