package com.rafaelvincensi.backstage.post;

import java.util.List;

public interface PostRepository<Post, UUID> {

    List<Post> findByUserId(UUID id);
}
