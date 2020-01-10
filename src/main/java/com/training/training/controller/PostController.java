package com.training.training.controller;

import com.training.training.client.PostClient;
import com.training.training.dto.PostDTO;
import com.training.training.entity.Post;
import com.training.training.exception.BadRequestException;
import com.training.training.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

@RestController
@Log4j2
public class PostController {

    private PostClient postClient;
    private PostService postService;
    private SseEmitter postEmitter;
    private AtomicInteger userCount = new AtomicInteger(0);
    private List<Consumer<PostDTO>> listeners = new CopyOnWriteArrayList<>();

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
    public PostDTO createPost(@RequestBody PostDTO post) throws IOException {
        post = postService.createPost(post);
        for (var listener : listeners) {
            listener.accept(post);
        }
//        var failedEmitters = new ArrayList<SseEmitter>();
//        for (SseEmitter sseEmitter : allEmitters) {
//            try {
//                sseEmitter.send(post);
//            } catch (IOException e) {
//                log.error(e);
//                failedEmitters.add(sseEmitter);
//            }
//        }
//        this.allEmitters.removeAll(failedEmitters);
        return post;
    }

    @GetMapping(value = "/sse")//, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostDTO> testSse(PostDTO postDTO) {
        userCount.incrementAndGet();
        return Flux.create(sink -> listeners.add(sink::next));
    }

    @GetMapping(value = "/user/count", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> userCount() {
        return Flux.interval(Duration.of(1, ChronoUnit.SECONDS))
                .map(e -> this.userCount.get());
    }

}
