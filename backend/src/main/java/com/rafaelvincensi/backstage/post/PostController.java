package com.rafaelvincensi.backstage.post;

import com.rafaelvincensi.backstage.post.dto.PostResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<PostResponseDTO> createPost(@RequestParam MultipartFile file){
        var postFile = postService.createPost(file);
        var savedPost = PostResponseDTO.fromEntity(postFile);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

}
