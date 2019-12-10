package com.training.training.controller;

import com.training.training.client.PostClient;
import com.training.training.dto.PostDTO;
import com.training.training.exception.BadRequestException;
import com.training.training.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private PostClient postClient;
    private PostService postService;

    PostController(PostClient postClient, PostService postService) {

        this.postClient = postClient;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Page<PostDTO> getPosts(@RequestParam(value = "userId", required = false) Long userId, Pageable pageable) {
        if (userId == null) {
            return postService.getAll(pageable);
        }
        return postService.getAllByUserId(pageable, userId);
    }

    @GetMapping("/posts/{id}")
    public PostDTO getPostsById(@PathVariable Long id) {
        if (id == null) {
            throw new BadRequestException();
        }
        return postClient.getAllPostById(id);
    }

    @PostMapping("/posts")
    public PostDTO createPost(@RequestBody PostDTO post) {
        return postService.createPost(post);
    }
}
