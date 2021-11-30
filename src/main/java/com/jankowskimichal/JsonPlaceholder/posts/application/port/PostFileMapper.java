package com.jankowskimichal.JsonPlaceholder.posts.application.port;

import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;

public interface PostFileMapper {
    String getFileName(Post post);

    String getFileContent(Post post);
}
