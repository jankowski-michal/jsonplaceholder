package com.jankowskimichal.JsonPlaceholder.posts.application;

import com.jankowskimichal.JsonPlaceholder.network.port.RestService;
import com.jankowskimichal.JsonPlaceholder.posts.application.port.PostUseCase;
import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;
import com.jankowskimichal.JsonPlaceholder.storage.port.FileStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
class PostService implements PostUseCase {

    private final RestService restService;
    private final FileStorage fileStorage;

    @Override
    public void fetchAndStorePosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = restService
                .getJson(url, Post[].class)
                .orElse(new Post[0]);
        Arrays.stream(posts).forEach(this::storePost);
    }

    private void storePost(Post post) {
        fileStorage.save(post.getTitle(), post.toString());
    }
}
