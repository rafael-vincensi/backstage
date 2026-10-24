package com.rafaelvincensi.backstage.post;

import com.rafaelvincensi.backstage.infra.S3StorageService;
import com.rafaelvincensi.backstage.user.User;
import com.rafaelvincensi.backstage.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final S3StorageService s3Service;

    public PostService(UserService userService, PostRepository postRepository, S3StorageService s3Service) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.s3Service = s3Service;
    }

    public Post createPost(MultipartFile file){
        String email = (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        User user = userService.findUserByEmail(email);

        String imageUrl = s3Service.uploadFile(file);

        Post post = new Post();

        post.setImageUrl(imageUrl);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);

        return postRepository.save(post);
    }

    public List<Post> getPostsByUser(UUID id){
        return postRepository.findByUserId(id);
    }


}
