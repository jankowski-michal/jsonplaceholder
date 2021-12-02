package com.jankowskimichal.JsonPlaceholder.posts.application.port;

import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;

import java.util.List;

public interface PostFileMapper {
    String getFileName(Post post);

    String getFileName(Long id);

    String getFileContent(Post post);
    String getFileContent(List<Post> posts);
}
