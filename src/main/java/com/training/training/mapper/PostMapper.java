package com.training.training.mapper;

import com.training.training.dto.PostDTO;
import com.training.training.entity.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "body", target = "content")
    @Mapping(target = "id", ignore = true)
    Post mapToEntity(PostDTO postDTO);

    @InheritInverseConfiguration
    @Mapping(source = "user.name", target = "name")
    PostDTO mapToDto(Post post);

    List<PostDTO> mapToList(List<Post> postList);
}
