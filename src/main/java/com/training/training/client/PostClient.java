package com.training.training.client;

import com.training.training.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post", url = "https://jsonplaceholder.typicode.com")
public interface PostClient {

        @GetMapping("/posts")
        List<PostDTO> getAllPosts();

        @GetMapping("/posts")
        List<PostDTO> getAllPostsByUserId(@RequestParam(name = "userId") Long userId);

        @RequestMapping("/posts/{id}")
        PostDTO getAllPostById(@PathVariable(name = "id") Long id);

        @PostMapping("/posts")
        PostDTO createPost(PostDTO post);
}
