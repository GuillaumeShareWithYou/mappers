package com.training.training.service;

import com.training.training.client.PostClient;
import com.training.training.dto.PostDTO;
import com.training.training.entity.Post;
import com.training.training.mapper.PostMapper;
import com.training.training.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostMapper postMapper;
    private PostRepository postRepository;

    PostService(PostMapper postMapper, PostRepository postRepository) {

        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    public Page<PostDTO> getAll(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postMapper::mapToDto);
    }
    public Page<PostDTO> getAllByUserId(Pageable pageable, Long userId) {
        return postRepository.findAllByUserId(pageable, userId)
                .map(postMapper::mapToDto);
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.mapToEntity(postDTO);
        post = postRepository.save(post);
        return postMapper.mapToDto(post);
    }
}
